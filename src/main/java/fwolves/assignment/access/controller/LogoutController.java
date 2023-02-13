package fwolves.assignment.access.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	@GetMapping("/home/logout")
	public String logout() {
		return "/user/index";
	}

}
