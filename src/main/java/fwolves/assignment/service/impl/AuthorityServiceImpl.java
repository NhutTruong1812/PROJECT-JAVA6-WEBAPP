package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Authority;
import fwolves.assignment.repository.AuthorityRepository;
import fwolves.assignment.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public ResponseEntity<List<Authority>> get() {
		return ResponseEntity.ok(authorityRepository.findAll());
	}

	@Override
	public ResponseEntity<Authority> get(Long id) {
		Optional<Authority> authority = authorityRepository.findById(id);
		if (!authority.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(authority.get());
	}

	@Override
	public ResponseEntity<Authority> add(Authority data) {
		Authority authority = authorityRepository.save(data);
		System.err.println(authority.getUser().getFullname());
		return ResponseEntity.ok(authority);
	}

	@Override
	public ResponseEntity<Authority> update(Long id, Authority data) {
		Authority authority = authorityRepository.getById(id);
		if (authority == null) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		Authority updated = authorityRepository.save(data);
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<Authority> delete(Long id) {
		Optional<Authority> authority = authorityRepository.findById(id);
		if (authority.isPresent()) {
			ResponseEntity.notFound().build();
		}
		authorityRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<Authority>> getByRoleId(String roleId) {
		List<Authority> authorities = authorityRepository.getByRoleId(roleId);
		return ResponseEntity.ok(authorities);
	}

	@Override
	public ResponseEntity<Authority> getByUserIdAndRoleId(Long userId, String roleId) {
		Authority authority = authorityRepository.getByUserIdAndRoleId(userId, roleId);
		if (authority == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(authority);
	}

	@Override
	public ResponseEntity<List<Authority>> getByUserId(Long userId) {
		return ResponseEntity.ok(authorityRepository.getByUserId(userId));
	}

}
