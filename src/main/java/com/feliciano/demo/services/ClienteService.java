package com.feliciano.demo.services;

import com.feliciano.demo.dto.ClienteDTO;
import com.feliciano.demo.dto.ClienteNewDTO;
import com.feliciano.demo.repositories.ClienteRepository;
import com.feliciano.demo.repositories.EnderecoRepository;
import com.feliciano.demo.resources.domain.Cidade;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.resources.domain.Endereco;
import com.feliciano.demo.resources.domain.enums.Perfil;
import com.feliciano.demo.resources.domain.enums.TipoCliente;
import com.feliciano.demo.security.SpringSecurityUser;
import com.feliciano.demo.services.exceptions.AuthorizationException;
import com.feliciano.demo.services.exceptions.DataIntegrityException;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

    public Cliente find(Integer id) {
        SpringSecurityUser user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null); // it doesn't allow the passing of id to database
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityException("Não é possivel excluir entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findByEmail(String email) {
        SpringSecurityUser user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Access denied!");
        }
        Cliente cli = repo.findByEmail(email);
        if (cli != null) return cli;
        throw new ObjectNotFoundException("Object not found! id: " + user.getId() + ", Type: " + Cliente.class.getName());
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cliente = null;
        try {
            cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOrCnpj(),
                    TipoCliente.toEnum(objDto.getTipo()), per.encode(objDto.getSenha()));
            Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
            Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                    objDto.getBairro(), objDto.getCep(), cidade, cliente);
            cliente.getEnderecos().add(end);
            cliente.getTelefones().add(objDto.getTelefone1());
            if (objDto.getTelefone2() != null) {
                cliente.getTelefones().add(objDto.getTelefone2());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return cliente;
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
