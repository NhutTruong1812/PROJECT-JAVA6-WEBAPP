package fwolves.assignment.user.controller;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import fwolves.assignment.entity.User;
import fwolves.assignment.service.FileManagerService;
import fwolves.assignment.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	FileManagerService fileManagerService;

	@ModelAttribute
	public void session() {
		session.setAttribute("usersession", userService.getCurrentUser().getBody());
	}

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

	@GetMapping("/home/edit-profile")
	public String profileView(Model model) {
		model.addAttribute("account", userService.getCurrentUser().getBody());
		return "/user/editprofile";
	}

	@PostMapping("/home/edit-profile")
	public String profile(Model model, @RequestPart("avt") Optional<MultipartFile> avt,
			@Validated @ModelAttribute("account") User account, BindingResult errors) {
		User sessionUser = userService.getCurrentUser().getBody();

//		Check update validate
		if (!errors.hasErrors()) {
			System.err.println("Hasn't Error");
//			Check Email
			boolean checkConfirmEmail = true;
			ResponseEntity<User> checkUniqueEmail = userService.getByEmail(account.getEmail());
			if (checkUniqueEmail.hasBody()) {
				if (checkUniqueEmail.getBody().getId() != sessionUser.getId()) {
					checkConfirmEmail = false;
					System.err.println("Exist email");
					errors.addError(new FieldError("account", "email", "Email " + account.getEmail() + " đã tồn tại!"));
				}
				System.err.println("Email itself");
			} else {
				System.err.println("No exist email");
			}
//			Check avatar 
			try {
				File avttemp = fileManagerService.save(avt.get(), "/images/user", "avt");
				account.setAvatar(avttemp.getName());
				System.err.println("ASoulut path: " + avttemp.getAbsolutePath());
				System.err.println("Has Images Avatar");
			} catch (Exception e) {
//				Check update avatar
				if (avt.get().isEmpty()) {
					account.setAvatar(sessionUser.getAvatar());
					System.err.println("Hasn't Update avatar");
				}
				System.err.println(e);
			}
//			Valid
			if (checkConfirmEmail) {
//				Show entity
				System.err.println("==================================================================");
				System.err.println("ID: " + account.getId());
				System.err.println("Fullname: " + account.getFullname());
				System.err.println("User: " + account.getUsername());
				System.err.println("Pas: " + account.getPassword());
				System.err.println("Email: " + account.getEmail());
				System.err.println("Phone: " + account.getPhoneNumber());
				System.err.println("Avatar: " + account.getAvatar());
				System.err.println("==================================================================");
//				set Data static 
				account.setActivated(sessionUser.getActivated());
				account.setRegisterDate(sessionUser.getRegisterDate());
				userService.update(sessionUser.getId(), account);
				System.err.println("Update user successful");
				return "redirect:/access/login";
			} else {
				System.err.println("Update user faild");
			}
		} else {
			System.err.println("Has Erorr");
		}

		return "/user/editprofile";
	}
}
