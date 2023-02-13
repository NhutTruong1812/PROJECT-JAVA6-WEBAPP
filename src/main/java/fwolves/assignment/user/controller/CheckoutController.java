package fwolves.assignment.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fwolves.assignment.entity.User;
import fwolves.assignment.service.AddressService;
import fwolves.assignment.service.CartService;
import fwolves.assignment.service.UserService;

@Controller
public class CheckoutController {

	@Autowired
	CartService cartService;

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

	@GetMapping("/home/checkout")
	public String checkoutView(Model model) {
		return "/user/checkout";
	}

	@PostMapping("/home/checkout")
	public String checkout(Model model) {

		return "user/checkout";
	}

}
