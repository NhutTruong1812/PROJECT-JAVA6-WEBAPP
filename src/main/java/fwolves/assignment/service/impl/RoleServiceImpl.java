package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Role;
import fwolves.assignment.repository.RoleRepository;
import fwolves.assignment.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ResponseEntity<List<Role>> get() {
		return ResponseEntity.ok(roleRepository.findAll());
	}

	@Override
	public ResponseEntity<Role> get(String id) {
		Optional<Role> product = roleRepository.findById(id);
		if (!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.get());
	}

	@Override
	public ResponseEntity<Role> add(Role data) {
		Role role = roleRepository.save(data);
		return ResponseEntity.ok(role);
	}

	@Override
	public ResponseEntity<Role> update(String id, Role data) {
		Optional<Role> existOptional = roleRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Role updated = roleRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<Role> delete(String id) {
		Optional<Role> existOptional = roleRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		roleRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
