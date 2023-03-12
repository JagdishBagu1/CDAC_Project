//package com.cdac.security;
//
//import com.cdac.custom_exceptions.ResourceNotFoundException;
//import com.cdac.dtos.UserDTO;
//import com.cdac.entities.User;
//import com.cdac.repositories.UserRepo;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Loading user from database by email
//        UserDTO userDTO = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("No user found with email: " + username));
//        User user = mapper.map(userDTO, User.class);
//
//        return null;
//    }
//
//}
