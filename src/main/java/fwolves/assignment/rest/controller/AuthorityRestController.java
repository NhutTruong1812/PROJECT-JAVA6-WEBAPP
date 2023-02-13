package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.Authority;
import fwolves.assignment.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authority")
public class AuthorityRestController {

	@Autowired
	private AuthorityService authorityService;

	@GetMapping
	public ResponseEntity<List<Authority>> get() {
		return (ResponseEntity<List<Authority>>) authorityService.get();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Authority> get(Long id) {
		return authorityService.get(id);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Authority>> getByUserId(@PathVariable("userId") Long userId){
		return authorityService.getByUserId(userId);
	}

	@GetMapping("/role/{id}")
	public ResponseEntity<List<Authority>> getByRoleId(@PathVariable("id") String roleId) {
		return authorityService.getByRoleId(roleId);
	}

	@GetMapping("/user/{userId}/role/{roleId}")
	public ResponseEntity<Authority> getByRoleIdAndUserid(@PathVariable("userId") Long userId,
			@PathVariable("roleId") String roleId) {
		return authorityService.getByUserIdAndRoleId(userId, roleId);
	}

	@PostMapping
	public ResponseEntity<Authority> post(@RequestBody Authority data) {
		data.setId(null);
		return authorityService.add(data);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Authority> put(@PathVariable("id") Long id, @RequestBody Authority data) {
		return authorityService.update(id, data);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Authority> delete(@PathVariable("id") Long id) {
		return authorityService.delete(id);
	}
}
