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

import fwolves.assignment.entity.Address;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.AddressService;
import fwolves.assignment.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/address")
public class AddressRestController {

	@Autowired
	private AddressService addressService;


	@Autowired
	private UserService userService;

//	
//	@GetMapping
//	public ResponseEntity<List<Address>> get() {
//		return addressService.get();
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> get(Long id) {

		return addressService.get(id);
	}

	@PostMapping
	public ResponseEntity<Address> post(@RequestBody Address data) {
		return addressService.add(data);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Address> put(@PathVariable("id") Long id, @RequestBody Address data) {
		return addressService.update(id, data);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		addressService.delete(id);
	}

	@GetMapping
	public ResponseEntity<List<Address>> get() {
		return addressService.getAddressByUser(userService.getCurrentUser().getBody());
	}
}
