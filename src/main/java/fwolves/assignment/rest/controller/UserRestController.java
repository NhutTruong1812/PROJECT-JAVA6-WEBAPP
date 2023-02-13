package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> get() {
		return userService.get();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id) {
		return userService.get(id);
	}

	@GetMapping("/current")
	public ResponseEntity<User> getCurrent() {
		return userService.getCurrentUser();
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<User>> findByKeyword(@PathVariable("keyword") String keyword) {
		return userService.findByKeyword("%" + keyword + "%");
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> put(@PathVariable("id") Long id, @RequestBody User data) {
		return userService.update(id, data);
	}

}
