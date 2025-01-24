package com.carportal.abc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.carportal.abc.dao.User;
import com.carportal.abc.service.RegService;


@Controller
public class RegController {
	
	@Autowired
	private RegService regService;

	@GetMapping("/register")
	public String registerPage(Map<String, Object> model) {
		System.out.println("registration page is loaded");
		User user = new User();
		model.put("user", user);
		return "register";
	}
	
	@PostMapping("/registered")
	public String saveUser(@ModelAttribute("user")User user, Model model) {
		System.out.println("registration controller working");
		regService.saveUser(user);
		String register_success="<h5>Welcome to ABC Cars!</h5>";
		model.addAttribute("register_success",register_success);
		return "register";
	}
}
