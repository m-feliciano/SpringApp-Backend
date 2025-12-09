package com.feliciano.store.services;

import com.feliciano.store.dto.ClientDTO;
import com.feliciano.store.dto.ClientNewDTO;
import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.resources.domain.Address;
import com.feliciano.store.resources.domain.City;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.enums.Perfil;
import com.feliciano.store.resources.mappers.CategoryMapper;
import com.feliciano.store.security.SpringSecurityUser;
import com.feliciano.store.services.exceptions.AuthorizationException;
import com.feliciano.store.services.exceptions.DataIntegrityException;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository repository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;
    private final ImageService imageService;
    private final CategoryMapper categoryMapper;
    @Value("${img.prefix.client.profile}")
	private String prefix;

    @Autowired
    public ClientService(ClientRepository repository,
                         AddressService addressService,
                         PasswordEncoder passwordEncoder,
                         S3Service s3Service,
                         ImageService imageService,
                         CategoryMapper categoryMapper
    ) {
        this.repository = repository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
        this.s3Service = s3Service;
        this.imageService = imageService;
        this.categoryMapper = categoryMapper;
    }

	@Value("${img.profile.size}")
	private int size;

	public Client find(Integer id) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied!");
		}

        Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null); // it doesn't allow the passing of id to database
        obj = repository.save(obj);
        addressService.saveAll(obj.getAddress());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
        return repository.save(newObj);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
            repository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("Cannot delete related entities");
		}
	}

	public List<Client> findAll() {
        return repository.findAll();
	}

	public Client findByEmail(String email) {
		SpringSecurityUser user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied!");
		}
        Client cli = repository.findByEmail(email);
		if (cli != null)
			return cli;
		throw new ObjectNotFoundException(
				"Object not found! id: " + user.getId() + ", Type: " + Client.class.getName());
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                Direction.valueOf(direction),
                orderBy);

        return repository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}

    public Client toEntity(ClientNewDTO objDto) {
        Client client = categoryMapper.toEntity(objDto);
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        City city = City.builder().id(objDto.getCityId()).build();

        Address address = Address.builder()
                .street(objDto.getStreet())
                .number(objDto.getNumber())
                .complement(objDto.getComplement())
                .district(objDto.getDistrict())
                .cep(objDto.getCep())
                .client(client)
                .city(city)
                .build();

        client.getAddress().add(address);
        client.getPhones().add(objDto.getPhone1());

        if (objDto.getPhone2() != null) {
            client.getPhones().add(objDto.getPhone2());
        }

        return client;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		SpringSecurityUser user = UserService.authenticated();
        if (user == null) throw new AuthorizationException("Access denied!");

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

    public ClientDTO toDTO(Client obj) {
        return new ClientDTO(obj.getId(), obj.getName(), obj.getEmail());
    }
}
