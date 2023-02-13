package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.User;
import fwolves.assignment.repository.CartRepository;
import fwolves.assignment.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public ResponseEntity<Cart> add(Cart data) {
		cartRepository.save(data);
		return ResponseEntity.ok(data);
	}

	@Override
	public ResponseEntity<Cart> delete(Long cartId) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (!cart.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cartRepository.deleteById(cartId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<Cart>> get() {
		return ResponseEntity.ok(cartRepository.findAll());
	}

	@Override
	public ResponseEntity<Cart> get(Long id) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (!cart.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cart.get());
	}

	@Override
	public ResponseEntity<Cart> update(Long id, Cart data) {
		Optional<Cart> cartOptional = cartRepository.findById(id);
		if (!cartOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		Cart updatedCart = cartRepository.save(data);
		if (updatedCart == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updatedCart);
	}

	@Override
	public ResponseEntity<Cart> getCartByUserId(Long userId) {
		Cart cart = cartRepository.getByUserId(userId);
		if (cart == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cart); 
	}

}
