package com.cdac.security;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {

    @NotEmpty(message = "Email required.")
    private String email;

    @NotEmpty(message = "Password required.")
    private String password;

}
