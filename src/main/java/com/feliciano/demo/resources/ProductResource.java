package com.feliciano.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feliciano.demo.dto.ProductDTO;
import com.feliciano.demo.resources.domain.Product;
import com.feliciano.demo.resources.utils.URL;
import com.feliciano.demo.services.ProductService;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "name", defaultValue = "0") String name,
			@RequestParam(value = "categories", defaultValue = "0") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeInList(categories);
		Page<Product> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(ProductDTO::new);
		return ResponseEntity.ok().body(listDto);
	}

}
