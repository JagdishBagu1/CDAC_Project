package com.cdac.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
}
