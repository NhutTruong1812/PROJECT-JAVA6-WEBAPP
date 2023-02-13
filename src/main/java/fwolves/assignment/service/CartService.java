package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Cart;

public interface CartService {

	public ResponseEntity<List<Cart>> get();

	public ResponseEntity<Cart> get(Long id);

	public ResponseEntity<Cart> add(Cart data);

	public ResponseEntity<Cart> update(Long id, Cart data);

	public ResponseEntity<Cart> delete(Long cartId);
	
	public ResponseEntity<Cart> getCartByUserId(Long userId);

}
