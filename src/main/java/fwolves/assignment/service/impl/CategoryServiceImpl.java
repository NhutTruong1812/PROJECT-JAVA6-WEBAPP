package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Category;
import fwolves.assignment.repository.CategoryRepository;
import fwolves.assignment.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ResponseEntity<List<Category>> get() {
		return ResponseEntity.ok(categoryRepository.findAll());
	}

	@Override
	public ResponseEntity<Category> get(String id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		if (!categoryOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryOptional.get());
	}

	@Override
	public ResponseEntity<Category> add(Category data) {
		if (categoryRepository.existsById(data.getId())) {
			return ResponseEntity.badRequest().build();
		}
		data.setDeleted(false);
		Category addedCategory = categoryRepository.save(data);
		if (addedCategory == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(addedCategory);
	}

	@Override
	public ResponseEntity<Category> update(String id, Category data) {
		Optional<Category> existOptional = categoryRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		Category updatedCategory = categoryRepository.save(data);
		if (updatedCategory == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updatedCategory);
	}

	@Override
	public ResponseEntity<Category> delete(String id) {
		Optional<Category> existOptional = categoryRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<Category>> findAllCategory() {
		return ResponseEntity.ok(categoryRepository.findAllCategory());
	}

	public ResponseEntity<List<Category>> get(boolean deleted) {
		return ResponseEntity.ok(categoryRepository.findAll(deleted));
	}

}
