package com.trimzo.service;

import com.trimzo.dto.request.LoginRequest;
import com.trimzo.dto.request.RegisterRequest;
import com.trimzo.dto.response.AuthResponse;
import com.trimzo.entity.User;
import com.trimzo.exception.InvalidCredentialsException;
import com.trimzo.repository.UserRepository;
import com.trimzo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Registers a new user.
     */
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // BCrypt hash
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    /**
     * Authenticates user and returns JWT token.
     */
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setExpiresIn(86400000);
        response.setUserName(user.getName());

        return response;
    }
}