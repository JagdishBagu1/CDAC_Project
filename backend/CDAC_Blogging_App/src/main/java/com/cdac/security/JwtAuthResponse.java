package com.cdac.security;

import com.cdac.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {

    private String token;

    private UserDTO user;

}
