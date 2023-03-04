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
    public User getUserById(long id) {
        Optional<User> userRes = userRepo.findById(id);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the id: " + id);

        return userRes.get();
    }

    // Update the existing user in the DB -
    public User updateUser(long id, UserDTO updatedUserDTO) {
        Optional<User> userRes = userRepo.findById(id);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the id: " + id);

        User user = dtoToUser(updatedUserDTO);
        user.setId(id);

        user = userRepo.save(user);

        return user;
    }

    // Delete the existing user from the DB -
    public User deleteUser(long id) {
        Optional<User> userRes = userRepo.findById(id);
        if (userRes.isEmpty())
            throw new ResourceNotFoundException("User not found with the id: " + id);

        userRepo.deleteById(id);

        return userRes.get();
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
