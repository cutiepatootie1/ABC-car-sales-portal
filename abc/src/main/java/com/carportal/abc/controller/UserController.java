package com.carportal.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carportal.abc.dao.User;
import com.carportal.abc.service.RegService;

@Controller
public class UserController {
	
	@Autowired
	private RegService regService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/login_error")
	public String onLoginError(Model model) {
		String error_msg="Incorrect username or password, please re-enter login details";
		model.addAttribute("error_str", error_msg);
		return "login";
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('admin')")
	public String viewUsers(Model model) {
		List<User> users = regService.listAll();
		if(!CollectionUtils.isEmpty(users)) {
			model.addAttribute("userlist",users);
		}
		return "admin";
	}
	
	@GetMapping("/delete_user")
	@PreAuthorize("hasRole('admin')")
	public String deleteUser(@RequestParam long id) {
		regService.delete(id);
		return "redirect:users";
	}
}
