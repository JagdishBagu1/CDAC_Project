package com.cdac.controllers;

import com.cdac.dtos.UserDTO;
import com.cdac.security.*;
import com.cdac.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomUserDetailService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    ResponseEntity<JwtAuthResponse> controlRegisterUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authenticationService.register(userDTO));
    }

    @PostMapping("/login")
    ResponseEntity<JwtAuthResponse> controlCreateToken(@Valid @RequestBody JwtAuthRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
