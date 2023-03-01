package com.cdac.services;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.UserDTO;
import com.cdac.entities.User;
import com.cdac.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create user in the DB -
    public User insertUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user = userRepo.save(user);

        return user;
    }

    // Read all the users from DB -
    public List<User> getAllUsers() {
        List<User> users = null;

        users = userRepo.findAll();

        return users;
    }

    // Find a user by email -
    public User getUserByEmail(String email) {
        User user = null;

        Optional<User> userRes = userRepo.findByEmail(email);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the email " + email);

        user = userRes.get();

        return user;
    }

    // Update the existing user in the DB -
    public User updateUser(String email, UserDTO updatedUserDTO) {
        if(!email.equals(updatedUserDTO.getEmail()))
            throw new RuntimeException("There is ambiguity in request info.");

        Optional<User> userRes = userRepo.findByEmail(email);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the email " + email);

        User user = dtoToUser(updatedUserDTO);
        user.setId(userRes.get().getId());

        user = userRepo.save(user);

        return user;
    }

    // Delete the existing user from the DB -
    public User deleteUser(String email) {
        Optional<User> userRes = userRepo.findByEmail(email);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the email " + email);

        userRepo.deleteById(userRes.get().getId());

        return userRes.get();
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
