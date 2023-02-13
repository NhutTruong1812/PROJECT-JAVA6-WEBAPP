package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.Category;
import fwolves.assignment.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;

	@GetMapping()
	public List<Category> getAll() {
		return categoryService.findAllCategory().getBody();
	}

	@PostMapping
	public Category create(@RequestBody Category category) {
		return categoryService.add(category).getBody();
	}

	@PutMapping("{id}")
	public Category update(@PathVariable("id") String id, @RequestBody Category category) {
		return categoryService.update(id, category).getBody();
	}

	@PutMapping("/delete/{id}")
	public Category delete(@PathVariable("id") String id, @RequestBody Category category) {
		category.setDeleted(true);
		return categoryService.update(id, category).getBody();
	}

	@GetMapping("{id}")
	public Category getId(@PathVariable("id") String id) {
		return categoryService.get(id).getBody();
	}

	@GetMapping("/cate")
	public ResponseEntity<List<Category>> get() {
		return categoryService.get(false);
	}
}
