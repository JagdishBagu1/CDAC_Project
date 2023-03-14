package com.cdac.services;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.UserDTO;
import com.cdac.entities.Role;
import com.cdac.entities.User;
import com.cdac.repositories.RoleRepo;
import com.cdac.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final long DEFAULT_ROLE_ID = 2;

    // Create user in the DB -
    public UserDTO insertUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findById(DEFAULT_ROLE_ID).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + DEFAULT_ROLE_ID));
        List<Role> roles = List.of(role);
        user.setRoles(roles);

        user = userRepo.save(user);
        userDTO = userToDTO(user);

        return userDTO;
    }

    // Read all the users from DB -
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map((user) -> userToDTO(user)).collect(Collectors.toList());
    }

    // Find a user by email -
    public UserDTO getUserById(long id) {
        Optional<User> userRes = userRepo.findById(id);
        if (userRes.isEmpty()) throw new ResourceNotFoundException("User not found with the id: " + id);

        return userToDTO(userRes.get());
    }

    // Update the existing user in the DB -
    public UserDTO updateUser(long id, UserDTO updatedUserDTO) {
        getUserById(id);
        User user = dtoToUser(updatedUserDTO);
        user.setId(id);

        user = userRepo.save(user);

        return userToDTO(user);
    }

    // Delete the existing user from the DB -
    public UserDTO deleteUser(long id) {
        UserDTO userDTO = getUserById(id);
        userRepo.deleteById(id);

        return userDTO;
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
