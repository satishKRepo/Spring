package com.satish.sprinboot.web.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LogInService {

	public boolean validateUser(String name , String password) {
		
		return "in28Minutes".equalsIgnoreCase(name) && "kesharwani".equalsIgnoreCase(password);
	}
}
