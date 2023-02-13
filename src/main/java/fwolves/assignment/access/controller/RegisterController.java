package fwolves.assignment.access.controller;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import fwolves.assignment.entity.Authority;
import fwolves.assignment.entity.Cart;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.AuthorityService;
import fwolves.assignment.service.CartService;
import fwolves.assignment.service.FileManagerService;
import fwolves.assignment.service.RoleService;
import fwolves.assignment.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	HttpServletRequest req;

	@Autowired
	UserService userService;

	@Autowired
	FileManagerService fileManagerService;

	@Autowired
	AuthorityService authorityService;

	@Autowired
	RoleService roleService;

	@Autowired
	CartService cartService;

	@GetMapping("/access/register")
	public String registerView(Model model) {
		User account = new User();
		model.addAttribute("account", account);
		return "user/register";
	}

	@PostMapping("/access/register")
	public String register(Model model, @RequestPart("avt") Optional<MultipartFile> avt,
			@Validated @ModelAttribute("account") User account, BindingResult errors) {

		if (!errors.hasErrors()) {
//			Show entity
			System.err.println("Fullname: " + account.getFullname());
			System.err.println("User: " + account.getUsername());
			System.err.println("Pas: " + account.getPassword());
			System.err.println("Email: " + account.getEmail());
			System.err.println("Phone: " + account.getPhoneNumber());

//			Check ConfirmPasword
			String confirmpassword = req.getParameter("confirmpassword");
			boolean checkConfirmPassword = true;
			if ((confirmpassword.isEmpty()) || (!confirmpassword.equals(account.getPassword()))) {
				System.err.println("Confirmpassword no valid");
				model.addAttribute("errorconfirm", "Xác nhận mật khẩu không hợp lệ!");
				checkConfirmPassword = false;
			}

//			Check Username
			boolean checkConfirmUsername = true;
			ResponseEntity<User> checkUniqueUsername = userService.getByUsername(account.getUsername());
			if (checkUniqueUsername.hasBody()) {
				System.err.println("Exist username");
				errors.addError(
						new FieldError("account", "username", "Username " + account.getUsername() + " đã tồn tại!"));
				checkConfirmUsername = false;
			} else {
				System.err.println("No exist username");
			}
//			Check Email
			boolean checkConfirmEmail = true;
			ResponseEntity<User> checkUniqueEmail = userService.getByEmail(account.getEmail());
			if (checkUniqueEmail.hasBody()) {
				checkConfirmEmail = false;
				errors.addError(new FieldError("account", "email", "Email " + account.getEmail() + " đã tồn tại!"));
				System.err.println("Exist email");
			} else {
				System.err.println("No exist email");
			}

//			Check avatar
			boolean checkImage = true;
			try {
				File avttemp = fileManagerService.save(avt.get(), "/images/user", "avt");
				account.setAvatar(avttemp.getName());
				System.err.println("ASoulut path: " + avttemp.getAbsolutePath());
				System.err.println("Has Images Avatar");
			} catch (Exception e) {
				checkImage = false;

				System.err.println("Hasn't Images Avatar");
				System.err.println(e);
				model.addAttribute("erroravatar", "Vui lòng chọn ảnh đại diện");
			}
			System.err.println("Avatar name: " + account.getAvatar());

//			Valid
			if ((checkConfirmPassword) && (checkConfirmEmail) && (checkConfirmUsername) && (checkImage)) {
				User usernew = userService.add(account).getBody();
				Authority authority = new Authority();
				authority.setUser(usernew);
				authority.setRole(roleService.get("GUEST").getBody());
				Authority authoritynew = authorityService.add(authority).getBody();
				System.err.println("Add authority successfull " + authoritynew.getUser().getUsername() + " Role: "
						+ authoritynew.getRole().getName());
				Cart cart = new Cart();
				cart.setUser(usernew);
				cartService.add(cart);
				System.err.println("Add user successful");
				return "/user/login";
			} else {
				System.err.println("Add user faild");
			}
		}
		return "/user/register";
	}
}
