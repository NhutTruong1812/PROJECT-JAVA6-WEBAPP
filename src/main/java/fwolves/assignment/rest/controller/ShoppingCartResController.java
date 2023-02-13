package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.CartItem;
import fwolves.assignment.service.ShoppingCartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/cart")
public class ShoppingCartResController {
	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping()
	public ResponseEntity<List<CartItem>> get() {
		return shoppingCartService.get();
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId") Long userId) {
		return shoppingCartService.getCartByUserId(userId);
	}

	@GetMapping("/total")
	public ResponseEntity<Double> getTotal() {
		return shoppingCartService.getTotal();
	}

	@GetMapping("/items")
	public ResponseEntity<List<CartItem>> getCartItemsOfCurrentUser() {
		return shoppingCartService.getCartItemsOfCurrentUser();
	}

	@GetMapping("/item/product/{id}")
	public ResponseEntity<CartItem> getItemByProductId(@PathVariable("id") Long productId) {
		return shoppingCartService.getItemByProductId(productId);
	}

	@GetMapping("/cost/{id}")
	public ResponseEntity<Double> getCost(@PathVariable("id") Long id) {
		return shoppingCartService.getCost(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CartItem> put(@PathVariable("id") Long cartId, @RequestBody Integer quantity) {
		return shoppingCartService.update(cartId, quantity);
	}

	@PostMapping
	public ResponseEntity<CartItem> post(@RequestBody CartItem cartItem) {
		return shoppingCartService.add(cartItem);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CartItem> delete(@PathVariable("id") Long id) {
		return shoppingCartService.delete(id);
	}

	@DeleteMapping("/clear")
	public ResponseEntity<CartItem> clear() {
		return shoppingCartService.clear();
	}

}
