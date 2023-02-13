package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Authority;

public interface AuthorityService {

	public ResponseEntity<List<Authority>> get();

	public ResponseEntity<Authority> get(Long id);
	
	public ResponseEntity<List<Authority>> getByUserId(Long userId);

	public ResponseEntity<Authority> add(Authority entity);

	public ResponseEntity<Authority> update(Long id, Authority entity);

	public ResponseEntity<Authority> delete(Long id);

	public ResponseEntity<List<Authority>> getByRoleId(String roleId);

	public ResponseEntity<Authority> getByUserIdAndRoleId(Long userId, String roleId);
}
