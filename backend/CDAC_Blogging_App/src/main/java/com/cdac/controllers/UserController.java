package com.cdac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.models.User;
import com.cdac.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	ResponseEntity<List<User>> sendAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping("/users")
	ResponseEntity<User> insertUser(@RequestBody User user) {
		System.out.println(user);
		return ResponseEntity.ok(userService.insertUser(user));
	}

}
