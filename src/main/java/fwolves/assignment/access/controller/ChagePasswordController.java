package fwolves.assignment.access.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fwolves.assignment.entity.User;
import fwolves.assignment.service.UserService;

@Controller
public class ChagePasswordController {

	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	
	@GetMapping("/home/change-password")
	public String changePassView() {
		return "/user/changepassword";
	}
	
	@PostMapping("/home/change-password")
	public String changePassword(Model model, User user, @RequestParam("confirmpass") String confirmpass, @RequestParam("password") String password) {
		ResponseEntity<User> userSession = userService.getByUsername("HAOTN");
		if(userSession.hasBody()) {
			System.err.println("AAAAAAAAAAAAAAAAAAA");
		}else {
			System.err.println("PPPPPPPPPPPPPPPPPPPP");
		}
		session.setAttribute("userSession", userSession.getBody());
		User sessionUser = (User) session.getAttribute("userSession");
		try {
			boolean check = true;
			password = user.getPassword();
			if(!password.isEmpty()) {
				if (!password.equals(confirmpass)) {
					model.addAttribute("errconfirmpass", "Xác nhận mật khẩu không trùng khớp");
					check = false;
				} else{
					sessionUser.setPassword(password);
					userService.update(sessionUser.getId(), sessionUser);
					model.addAttribute("message", "Đổi mật khẩu thành công");
					return "redirect:/access/login";
				}
			}else {
				model.addAttribute("message", "Đổi mật khẩu thất bại");
				return "/user/changepassword";
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return "/user/changepassword";
	}
}
