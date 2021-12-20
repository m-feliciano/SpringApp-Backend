package com.feliciano.demo.resources;

import java.net.URI;

import javax.validation.Valid;

import com.feliciano.demo.dto.CategoriaDTO;
import com.feliciano.demo.resources.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.feliciano.demo.resources.domain.Pedido;
import com.feliciano.demo.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) { // @RequestBody converts JSON to object body
		obj = service.insert(obj);
		ServletUriComponentsBuilder.fromCurrentRequest();
		URI uri = UriComponentsBuilder.fromPath("/{id}").buildAndExpand(obj.getItens()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
														@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
														@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
														@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
