package com.feliciano.store.services;

import com.feliciano.store.repositories.CategoryRepository;
import com.feliciano.store.repositories.ProductRepository;
import com.feliciano.store.resources.domain.Category;
import com.feliciano.store.resources.domain.Product;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Product find(Integer id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Product.class.getName()));
    }

    public Page<Product> search(String name,
                                List<Integer> ids,
                                Integer page,
                                Integer linesPerPage,
                                String orderBy,
                                String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);
        return repository.findDistinctByNameContainingIgnoreCaseAndCategoriesIn(name, categories, pageRequest);

    }
}
