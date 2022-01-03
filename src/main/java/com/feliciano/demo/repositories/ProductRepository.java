package com.feliciano.demo.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feliciano.demo.resources.domain.Category;
import com.feliciano.demo.resources.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, Collection<List<Category>> categories,
			Pageable pageable);

}
