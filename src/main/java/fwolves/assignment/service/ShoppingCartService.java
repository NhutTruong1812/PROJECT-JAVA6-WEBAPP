package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.CartItem;

public interface ShoppingCartService {

	public ResponseEntity<List<CartItem>> get();

	public ResponseEntity<CartItem> add(CartItem cartItem);

	public ResponseEntity<CartItem> update(Long id, Integer quantity);

	public ResponseEntity<CartItem> delete(Long id);

	public ResponseEntity<Double> getTotal();

	public ResponseEntity<CartItem> clear();

	public ResponseEntity<Double> getCost(Long id);

	public ResponseEntity<List<CartItem>> getCartItemsOfCurrentUser();

	public ResponseEntity<CartItem> getItemByProductId(Long productId);
	
	public ResponseEntity<Cart> getCartByUserId(Long userId);
}
