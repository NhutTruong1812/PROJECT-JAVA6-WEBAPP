package fwolves.assignment.access.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fwolves.assignment.service.SessionService;
import fwolves.assignment.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/access/login")
	public String loginView() {
		return "/user/login";
	}

	@PostMapping("/access/login")
	public String loginPost(@RequestParam("username") String username) {
		return "/user/login";
	}

	@RequestMapping("/access/login/success")
	public String loginPost() {
		return "redirect:/home/index";
	}

	@GetMapping("/oauth2/login/success")
	public String oauth2view(OAuth2AuthenticationToken oauth) {
		userService.loginFromOAuth2(oauth);
		return "redirect:/home/index";
	}
}
