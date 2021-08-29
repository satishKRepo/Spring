package com.satish.sprinboot.web.springbootfirstwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.satish.sprinboot.web.springbootfirstwebapplication.service.LogInService;

@Controller
public class LoginController {

	@Autowired
	LogInService logInService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap modelMap) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLogin(ModelMap modelMap, @RequestParam String name, @RequestParam String password) {
		boolean isValidUser = logInService.validateUser(name, password);
		if (!isValidUser) {
			modelMap.put("errorMessage", "Invalid Credentials");
			return "login";
		}

		modelMap.put("name", name);
		modelMap.put("password", password);
		return "welcome";
	}
}
