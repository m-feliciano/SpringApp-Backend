package com.feliciano.demo.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.feliciano.demo.dto.ClientDTO;
import com.feliciano.demo.dto.ClientNewDTO;
import com.feliciano.demo.repositories.AddressRepository;
import com.feliciano.demo.repositories.ClientRepository;
import com.feliciano.demo.resources.domain.Address;
import com.feliciano.demo.resources.domain.City;
import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.resources.domain.enums.Perfil;
import com.feliciano.demo.resources.domain.enums.ClientType;
import com.feliciano.demo.security.SpringSecurityUser;
import com.feliciano.demo.services.exceptions.AuthorizationException;
import com.feliciano.demo.services.exceptions.DataIntegrityException;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder per;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private int size;

	public Client find(Integer id) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied!");
		}

		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null); // it doesn't allow the passing of id to database
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddress());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("Cannot delete related entities");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
	}

	public Client findByEmail(String email) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied!");
		}
		Client cli = repo.findByEmail(email);
		if (cli != null)
			return cli;
		throw new ObjectNotFoundException(
				"Object not found! id: " + user.getId() + ", Type: " + Client.class.getName());
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objDto) {
		Client client = null;
		try {
			client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
					ClientType.toEnum(objDto.getType()), per.encode(objDto.getPassword()));
			City city = new City(objDto.getCityId(), null, null);
			Address end = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(),
					objDto.getDistrict(), objDto.getCep(), client, city);
			client.getAddress().add(end);
			client.getPhones().add(objDto.getPhone1());
			if (objDto.getPhone2() != null) {
				client.getPhones().add(objDto.getPhone2());
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return client;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied!");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}
}
