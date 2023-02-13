package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Address;
import fwolves.assignment.entity.User;

public interface AddressService {
	public ResponseEntity<List<Address>> get();

	public ResponseEntity<Address> get(Long id);

	public ResponseEntity<Address> add(Address address);

	public ResponseEntity<Address> update(Long addressId, Address address);

	public void delete(Long addressId);
	
	public ResponseEntity<List<Address>> getAddressByUser(User user);
}
