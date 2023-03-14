package com.cdac.security;

import com.cdac.dtos.UserDTO;
import com.cdac.entities.User;
import com.cdac.repositories.UserRepo;
import com.cdac.security.token.Token;
import com.cdac.security.token.TokenRepository;
import com.cdac.security.token.TokenType;
import com.cdac.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;


    public JwtAuthResponse register(JwtAuthRequest request) {
        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
        userDTO = userService.insertUser(userDTO);
        User savedUser = modelMapper.map(userDTO, User.class);

        String jwtToken = jwtUtils.generateToken(savedUser);

        saveUserToken(savedUser, jwtToken);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwtToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse authenticate(JwtAuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwtToken);

        return jwtAuthResponse;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
