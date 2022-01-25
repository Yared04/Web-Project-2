package com.gada.root;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid; 

import org.hibernate.property.access.spi.GetterFieldImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostsRepositary postRepo;
	@Autowired
	private PostService service2;
	@Autowired
	private UserService service;
	// @GetMapping("")
	// public String viewHomePage() {
	// 	return "home";
	// }
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		// model.addAttribute(attributeName, attributeValue)
		
		return "userRegister";
	}
	
	@PostMapping("/register")
	public String processRegister(User user) {
	 service.saveUserWithDefaultRole(user);
		return "redirect:/login";
	}
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "userLogin";}
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
			
		return "users";
	}
	@GetMapping("/users/edit/{id}")
public String editUser(@PathVariable("id") Integer id, Model model) {
    User user = service.get(id);
    List<Role> listRoles = service.listRoles();
    model.addAttribute("user", user);
    model.addAttribute("listRoles", listRoles);
    return "edit_form";
}
@GetMapping("/users/delete/{id}")
public String deleteUser(@PathVariable("id") Integer id, Model model) {
    userRepo.deleteById(id);
    return "redirect:/users";
}
@GetMapping("/listpost")
public String listPosts(Model model) {
	List<Posts> listPosts = service2.listAll();
		model.addAttribute("listPosts", listPosts);	
	return "posts";
}
@GetMapping("/listpost/edit/{id}")
public String editPost(@PathVariable("id") Long id, Model model) {
    Posts post = service2.get(id);
	model.addAttribute("post", post);
    return "editPosts";
}
@GetMapping("/listpost/delete/{id}")
public String deletePost(@PathVariable("id") Long id, Model model) {
    postRepo.deleteById(id);
    return "redirect:/listpost";
}
@PostMapping("/users/save")
public String saveUser(User user) {
    service.save(user);
     
    return "redirect:/users";
}
@PostMapping("/listpost/save")
public String updatePosts(@Valid @ModelAttribute("post") Posts post,
	Errors errors, Model model,
	@RequestParam("image") MultipartFile multipartFile)
	throws IOException {
   
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	if (fileName.contains("..")) {
		System.out.println("not valid file");
		return "editPosts";
	}
	  
	LocalDateTime ldt = LocalDateTime.now();
	post.setDate(ldt);
	try {
		byte[] image = Base64.getEncoder().encode(multipartFile.getBytes());
		String result = new String(image);
		System.out.println(result);
		post.setProductImg(image);
		post.setBase64Img(result);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if (errors.hasErrors()) {
		log.info(errors.toString());
		return "editPosts";

	}
	
	service2.save(post);

	return "redirect:/";
}
}
