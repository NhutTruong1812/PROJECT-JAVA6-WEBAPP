package fwolves.assignment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.User;
import fwolves.assignment.repository.UserRepository;
import fwolves.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<User> add(User user) {
		user.setActivated(true);
		user.setRegisterDate(new Date());
		User added = userRepository.save(user);
		return ResponseEntity.ok(added);
	}

	@Override
	public ResponseEntity<User> update(Long id, User data) {
		Optional<User> existOptional = userRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		User updated = userRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<User> delete(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<User>> get() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}

	@Override
	public ResponseEntity<User> getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		User user = userRepository.getByUserName(username);
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<User> get(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		UserDetails user = org.springframework.security.core.userdetails.User.withUsername(email).password(password)
				.roles("GUEST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public ResponseEntity<User> getByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}

	@Override
	public ResponseEntity<User> getByEmail(String email) {
		User user = userRepository.getByEmail(email);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<User> getByImage(String avatar) {
		User user = userRepository.getByImage(avatar);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<List<User>> findByKeyword(String keyword) {
		return ResponseEntity.ok(userRepository.findByKeyword(keyword));
	}
}
