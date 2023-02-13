package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Category;

public interface CategoryService {

	public ResponseEntity<List<Category>> get();

	public ResponseEntity<Category> get(String id);

	public ResponseEntity<Category> add(Category data);

	public ResponseEntity<Category> update(String id, Category data);

	public ResponseEntity<Category> delete(String id);

	public ResponseEntity<List<Category>> findAllCategory();

	public ResponseEntity<List<Category>> get(boolean deleted);
}
