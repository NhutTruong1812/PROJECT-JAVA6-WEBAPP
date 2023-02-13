package fwolves.assignment.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import fwolves.assignment.entity.User;
import fwolves.assignment.service.UserService;

@Controller
public class CartController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/home/cart")
	public String cartView() {
		return "user/cart";
	}

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

}
