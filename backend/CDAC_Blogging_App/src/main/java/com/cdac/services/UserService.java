package com.cdac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.models.User;
import com.cdac.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public User insertUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getAllUsers() {
		List<User> users = null;
		
		users = userRepo.findAll();
		
		return users;
	}
	
}
