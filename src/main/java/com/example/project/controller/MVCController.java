package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.models.LoginModel;
import com.example.project.models.UserModel;
import com.example.project.repository.UserRepository;
import com.example.project.service.EmailService;

@Controller
public class MVCController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@RequestMapping("/home/{role}")
	public String homePage(@PathVariable("role") String role , Model model) {
		model.addAttribute("role",role);
		return "home";
	}

	@RequestMapping("/userList")
	public String userList(Model model) {
		List<UserModel> userList = userRepository.findAll();
		model.addAttribute("userList", userList);
		System.out.println("Size is " + userList.size());
		return "userList";
	}

	@RequestMapping(value={"/","/register"})
	public String registerPage(@ModelAttribute("register") UserModel userModel, Model model) {
		return "register";
	}

	@RequestMapping("/login")
	public String loginPage(@ModelAttribute("login") LoginModel login, Model model) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logoutPage(Model model) {
		return "logout";
	}
	
	
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("login") LoginModel login, BindingResult bindingResult) {

		System.out.println(login.toString());
		if (bindingResult.hasErrors()) {
			System.out.println("Errors found");
			return "/";
		}
		UserModel user = userRepository.findByNameAndPwd(login.getUsername(), login.getPassword());
		if (null != user) {
			return "redirect:/home/"+user.getRole();
		} else {
			return "/error";
		}
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("register") UserModel register, BindingResult bindingResult) {
		System.out.println(register.toString());
		if (bindingResult.hasErrors()) {
			System.out.println("Errors found");
			return "/";
		}
		UserModel us = null;
		if (null != register.mobileNumber && !register.mobileNumber.isEmpty() && null != register.companyName
				&& !register.companyName.isEmpty()) {
			us = userRepository.save(new UserModel(register.username, register.getPassword(), register.firstname,
					register.lastname, register.gender, register.date_of_birth, register.country, register.zipcode,
					register.email, register.companyName, register.mobileNumber, "Critics"));
		} else {
			us = userRepository.save(new UserModel(register.username, register.getPassword(), register.firstname,
					register.lastname, register.gender, register.date_of_birth, register.country, register.zipcode,
					register.email, "Public User"));
		}
		System.out.println("success db " + us.getUserId());
		try {
		emailService.sendSimpleMessage("",us);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return "redirect:/login";
	}
}
