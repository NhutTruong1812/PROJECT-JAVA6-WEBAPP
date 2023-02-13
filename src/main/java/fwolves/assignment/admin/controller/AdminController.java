package fwolves.assignment.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fwolves.assignment.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;

	@GetMapping(value = { "/admin", "/admin/index" })
	public String adminView(Model model) {
		model.addAttribute("currentUser", userService.getCurrentUser());
		return "redirect:/admin/index.html";
	}

}
