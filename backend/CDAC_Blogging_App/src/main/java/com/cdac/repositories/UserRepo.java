package com.cdac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.models.User;

public interface UserRepo extends JpaRepository<User, String> {

}
