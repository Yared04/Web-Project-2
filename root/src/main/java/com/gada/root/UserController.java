package com.gada.root;

import java.util.List;

import org.hibernate.property.access.spi.GetterFieldImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService service;
	@GetMapping("")
	public String viewHomePage() {
		return "home";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		// model.addAttribute(attributeName, attributeValue)
		
		return "userRegister";
	}
	
	@PostMapping("/register")
	public String processRegister(User user) {
	 service.saveUserWithDefaultRole(user);
		return "home";
	}
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "userLogin";}
	
}
