package com.feliciano.store.resources;

import com.feliciano.store.dto.CategoryDTO;
import com.feliciano.store.resources.domain.Category;
import com.feliciano.store.resources.openapi.CategoryResourceOpenApi;
import com.feliciano.store.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryResource implements CategoryResourceOpenApi {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category obj = categoryService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto) { // @RequestBody converts JSON to
        Category category = categoryService.fromDTO(objDto);
        category = categoryService.insert(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto, @PathVariable Integer id) {
		Category obj = categoryService.fromDTO(objDto);
		obj.setId(id);
		categoryService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> listDto = list.stream().map(CategoryDTO::new).toList();
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = list.map(CategoryDTO::new);
		return ResponseEntity.ok().body(listDto);
	}

}
