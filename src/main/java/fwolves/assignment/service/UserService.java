package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import fwolves.assignment.entity.User;

public interface UserService {

	public ResponseEntity<List<User>> get();

	public ResponseEntity<User> get(Long id);

	public ResponseEntity<User> getCurrentUser();

	public ResponseEntity<User> add(User user);

	public ResponseEntity<User> update(Long userid, User user);

	public ResponseEntity<User> delete(Long userId);

	public ResponseEntity<User> getByEmail(String email);

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	public ResponseEntity<User> getByUsername(String username);

//	Register 

	public ResponseEntity<User> getByImage(String avatar);

	public ResponseEntity<List<User>> findByKeyword(String keyword);

}
