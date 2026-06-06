package com.trimzo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String token;

    private String tokenType = "Bearer";

    private long expiresIn;

    private String userName;
}