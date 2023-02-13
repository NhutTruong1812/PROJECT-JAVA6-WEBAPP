package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Role;

public interface RoleService {

	public ResponseEntity<List<Role>> get();

	public ResponseEntity<Role> get(String id);

	public ResponseEntity<Role> add(Role data);

	public ResponseEntity<Role> update(String id, Role data);

	public ResponseEntity<Role> delete(String id);
}
