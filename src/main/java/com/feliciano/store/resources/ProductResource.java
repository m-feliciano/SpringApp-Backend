package com.feliciano.store.resources;

import com.feliciano.store.dto.ProductDTO;
import com.feliciano.store.resources.domain.Product;
import com.feliciano.store.resources.openapi.ProductResourceOpenApi;
import com.feliciano.store.resources.utils.URL;
import com.feliciano.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductResource implements ProductResourceOpenApi {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product obj = productService.find(id);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                      @RequestParam(value = "categories", defaultValue = "") String categories,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeInList(categories);
        Page<Product> list = productService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok(listDto);
    }

}
