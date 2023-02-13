package fwolves.assignment.access.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.UserService;

@Controller
public class ForgotPasswordController {

	@GetMapping("/home/forgot-password")
	public String forgotView() {
		return "/user/forgotpassword";
	}

	@Autowired
	UserService userSV;

	@PostMapping("/home/forgot-password")
	public String post(Model m, @RequestParam("email") String email) {
		if (email.isEmpty()) {
			m.addAttribute("error", "Vui lòng nhập email!");
		} else {
			User tempuser = userSV.getByEmail(email).getBody();
			if (tempuser == null) {
				m.addAttribute("error", "Email này chưa được đăng ký!");
			} else {
				String temppass = resetpass(email);
				tempuser.setPassword(temppass);
				userSV.update(tempuser.getId(), tempuser);
				m.addAttribute("msg", "Đã gửi mật khẩu mới về mail " + email + " ...");
				return "redirect:/home/index";
			}
		}

		return "/user/forgotpassword";
	}

	@Autowired
	JavaMailSender sender;

	private String resetpass(String email) {
		// TODO Auto-generated method stub
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			//
			helper.setFrom("Latipee <support@latipee.com>");
			helper.setTo(email);
			helper.setSubject("Lấy lại mật khẩu - Latipee");
			String newpass = "";
			for (int i = 0; i < 6; i++) {
				int random = (int) Math.round(Math.random() * 9);
				newpass = newpass + random;
			}
			helper.setText("Hỗ trợ người dùng lấy lại mật khẩu...\n" + "Đây là mật khẩu mới: " + newpass);
			helper.setReplyTo("Latipee <support@latipee.com>");
			//
			sender.send(message);
			return newpass;

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

	}
}
