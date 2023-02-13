package fwolves.assignment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.CartItem;
import fwolves.assignment.entity.Product;
import fwolves.assignment.entity.User;
import fwolves.assignment.repository.CartItemRepository;
import fwolves.assignment.service.CartItemService;
import fwolves.assignment.service.CartService;
import fwolves.assignment.service.PriceHistoryService;
import fwolves.assignment.service.ProductService;
import fwolves.assignment.service.ShoppingCartService;
import fwolves.assignment.service.UserService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private PriceHistoryService priceHistoryService;

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<List<CartItem>> get() {
		List<CartItem> list = cartItemService.get().getBody();
		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<CartItem> add(CartItem cartItem) {
		User user = userService.getCurrentUser().getBody();
		Cart cart = user.getCart();
		cartItem.setCart(cart);
		cartItem.setCreateDate(new Date());
		CartItem added = cartItemService.add(cartItem).getBody();
		return ResponseEntity.ok(added);
	}

	@Override
	public ResponseEntity<CartItem> update(Long id, Integer quantity) {
		return cartItemService.update(id, quantity);
	}

	@Override
	public ResponseEntity<CartItem> delete(Long id) {
		return cartItemService.delete(id);
	}

	@Override
	public ResponseEntity<Double> getTotal() {
		List<CartItem> items = this.getCartItemsOfCurrentUser().getBody();
		Double total = 0.0;
		for (CartItem o : items) {
			total += o.getQuantity()
					* priceHistoryService.getNewestByProductId(o.getProduct().getId()).getBody().getPrice();
		}
		return ResponseEntity.ok(total);
	}

	@Override
	public ResponseEntity<CartItem> clear() {
		return cartItemService.clear();
	}

	@Override
	public ResponseEntity<Double> getCost(Long id) {
		CartItem cartItem = cartItemService.get(id).getBody();
		Double cost = cartItem.getQuantity()
				* priceHistoryService.getNewestByProductId(cartItem.getProduct().getId()).getBody().getPrice();
		return ResponseEntity.ok(cost);
	}

	@Override
	public ResponseEntity<List<CartItem>> getCartItemsOfCurrentUser() {
		User user = userService.getCurrentUser().getBody();
		Cart cart = user.getCart();
		return cartItemService.getByCartId(cart.getId());
	}

	@Override
	public ResponseEntity<CartItem> getItemByProductId(Long productId) {
		return cartItemService.getbyProductId(productId);
	}

	@Override
	public ResponseEntity<Cart> getCartByUserId(Long userId) {
		return cartService.getCartByUserId(userId);
	}

}
