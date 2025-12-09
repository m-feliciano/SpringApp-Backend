package com.feliciano.store.resources;

import com.feliciano.store.dto.ClientDTO;
import com.feliciano.store.dto.ClientNewDTO;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.openapi.ClientResourceOpenApi;
import com.feliciano.store.services.ClientService;
import com.feliciano.store.services.exceptions.DataIntegrityException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientResource implements ClientResourceOpenApi {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> find(@PathVariable Integer id) {
        Client obj = clientService.find(id);
        ClientDTO body = clientService.toDTO(obj);
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> find(@RequestParam(value = "value") String email) {
        Client obj = clientService.findByEmail(email);
        ClientDTO body = clientService.toDTO(obj);
        return ResponseEntity.ok(body);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDto) { // @RequestBody converts JSON to //
        Client obj = clientService.insert(clientService.toEntity(objDto));
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
        Client obj = clientService.fromDTO(objDto);
        obj.setId(id);
        clientService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clientService.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to delete a Customer who owns products!");
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> find() {
        List<Client> list = clientService.findAll();
        List<ClientDTO> listDto = list.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> listDto = list.map(ClientDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = clientService.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
