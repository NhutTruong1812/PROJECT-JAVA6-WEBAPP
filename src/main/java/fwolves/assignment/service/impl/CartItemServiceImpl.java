package fwolves.assignment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.CartItem;
import fwolves.assignment.entity.User;
import fwolves.assignment.repository.CartItemRepository;
import fwolves.assignment.service.CartItemService;
import fwolves.assignment.service.UserService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<List<CartItem>> get() {
		return ResponseEntity.ok(cartItemRepository.findAll());
	}

	@Override
	public ResponseEntity<CartItem> get(Long id) {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
		if (!cartItemOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartItemOptional.get());
	}

	@Override
	public ResponseEntity<CartItem> add(CartItem data) {
		data.setCreateDate(new Date());
		CartItem addedCartItem = cartItemRepository.save(data);
		if (addedCartItem == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(addedCartItem);
	}

	@Override
	public ResponseEntity<CartItem> update(Long id, Integer quantity) {
		CartItem cartItem = cartItemRepository.getById(id);
		if (cartItem == null) {
			return ResponseEntity.notFound().build();
		}
		cartItem.setQuantity(quantity);
		CartItem updated = cartItemRepository.save(cartItem);
		if (updated == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<CartItem> delete(Long id) {
		Optional<CartItem> cartItem = cartItemRepository.findById(id);
		if (!cartItem.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cartItemRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<CartItem>> getByCartId(Long cardId) {
		List<CartItem> list = cartItemRepository.getByCartId(cardId);
		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<CartItem> clear() {
		User user = userService.getCurrentUser().getBody();
		cartItemRepository.deleteByCartId(user.getCart().getId());
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<CartItem> getbyProductId(Long productid) {
		User user = userService.getCurrentUser().getBody();
		Cart cart = user.getCart();
		CartItem cartItem = cartItemRepository.getByCartIdAndProductId(cart.getId(), productid);
		return ResponseEntity.ok(cartItem);
	}

	public ResponseEntity<List<CartItem>> getCartItemByUser(User user) {
		List<CartItem> cartItems = cartItemRepository.getByUser(user).get();
		if (cartItems == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartItems);
	}

	@Override
	public void deleteCheckout(Long id) {
		cartItemRepository.deleteById(id);
	}

}
