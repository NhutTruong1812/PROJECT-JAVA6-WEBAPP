package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Address;
import fwolves.assignment.entity.User;
import fwolves.assignment.repository.AddressRepository;
import fwolves.assignment.service.AddressService;
import fwolves.assignment.service.UserService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public ResponseEntity<Address> add(Address address) {
		address.setUser(userService.getCurrentUser().getBody());
		address.setId(null);
		Address added = addressRepository.save(address);
		return ResponseEntity.ok(added);
	}

	@Override
	public ResponseEntity<Address> update(Long addressId, Address address) {
		Address exsit = addressRepository.getById(addressId);
		if (exsit == null) {
			return ResponseEntity.notFound().build();
		}
		address.setId(exsit.getId());
		Address updated = addressRepository.save(address);
		return ResponseEntity.ok(updated);
	}

	@Override
	public void delete(Long addressId) { 
		addressRepository.deleteById(addressId);  
	}

	@Override
	public ResponseEntity<List<Address>> get() {
		return ResponseEntity.ok(addressRepository.findAll());
	}

	@Override
	public ResponseEntity<Address> get(Long id) {
		Optional<Address> address = addressRepository.findById(id);
		if (!address.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(address.get());
	}

	@Override
	public ResponseEntity<List<Address>> getAddressByUser(User user) {
		List<Address> address = addressRepository.getByUser(user);
		if(address== null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(address);
	}

}
