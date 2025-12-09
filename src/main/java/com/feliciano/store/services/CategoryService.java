package com.feliciano.store.services;

import com.feliciano.store.dto.CategoryDTO;
import com.feliciano.store.repositories.CategoryRepository;
import com.feliciano.store.resources.domain.Category;
import com.feliciano.store.services.exceptions.DataIntegrityException;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category find(Integer id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(String.valueOf(id), Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null); // it doesn't allow the passing of id to database
        return repository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
        return repository.save(newObj);
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}

	public void delete(Integer id) {
        Category category = find(id);
        try {
            repository.delete(category);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a categoria that has products");
		}
	}

	public List<Category> findAll() {
        return repository.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);

	}

	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
}
