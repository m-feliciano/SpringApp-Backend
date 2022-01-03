package com.feliciano.demo.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.feliciano.demo.repositories.CategoryRepository;
import com.feliciano.demo.repositories.ProductRepository;
import com.feliciano.demo.resources.domain.Category;
import com.feliciano.demo.resources.domain.Product;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	@Autowired
	private CategoryRepository catRepo;

	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Product.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = catRepo.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriesIn(name, Collections.singleton(categories), pageRequest);

	}
}
