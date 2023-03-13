package com.cdac.controllers;

import com.cdac.security.CustomUserDetailService;
import com.cdac.security.JWTUtils;
import com.cdac.security.JwtAuthRequest;
import com.cdac.security.JwtAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private CustomUserDetailService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    ResponseEntity<JwtAuthResponse> controlCreateToken(@RequestBody JwtAuthRequest request) {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

}
