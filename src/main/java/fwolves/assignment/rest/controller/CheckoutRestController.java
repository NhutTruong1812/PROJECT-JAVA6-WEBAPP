package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.CartItem;
import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.OrderDetail;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.AuthorityService;
import fwolves.assignment.service.CartItemService;
import fwolves.assignment.service.CartService;
import fwolves.assignment.service.OrderDetailService;
import fwolves.assignment.service.OrderService;
import fwolves.assignment.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/checkout")
public class CheckoutRestController {
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("/carts")
	public ResponseEntity<List<CartItem>> get() { 
		return cartItemService.getCartItemByUser(userService.getCurrentUser().getBody());
	}
	
	@PostMapping("/order")
	public ResponseEntity<Order> post(@RequestBody Order order) {
		return orderService.add(order);
	}
	
	@PostMapping("/order/detail")
	public ResponseEntity<OrderDetail> postDt(@RequestBody OrderDetail data) { 
		return orderDetailService.add(data);
	}
	
	@DeleteMapping("/cart/{id}")
	public void delete(@PathVariable("id") Long id) {
		cartItemService.deleteCheckout(id);
	}
}
