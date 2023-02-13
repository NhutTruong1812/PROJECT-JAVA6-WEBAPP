package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.CartItem;
import fwolves.assignment.entity.User;

public interface CartItemService {

	public ResponseEntity<List<CartItem>> get();

	public ResponseEntity<List<CartItem>> getByCartId(Long cardId);

	public ResponseEntity<CartItem> getbyProductId(Long productid);

	public ResponseEntity<CartItem> get(Long id);

	public ResponseEntity<CartItem> add(CartItem data);

	public ResponseEntity<CartItem> update(Long id, Integer quantity);

	public ResponseEntity<CartItem> delete(Long id);

	public ResponseEntity<CartItem> clear();

	public ResponseEntity<List<CartItem>> getCartItemByUser(User user);

	public void deleteCheckout(Long id);
}
