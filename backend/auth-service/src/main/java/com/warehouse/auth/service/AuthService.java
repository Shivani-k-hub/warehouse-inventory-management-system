package com.warehouse.auth.service;

import com.warehouse.auth.dto.AuthRequest;
import com.warehouse.auth.dto.AuthResponse;
import com.warehouse.auth.dto.RegisterRequest;
import com.warehouse.auth.entity.UserCredential;
import com.warehouse.auth.exception
    .AuthException;
import com.warehouse.auth.repository
    .UserCredentialRepository;
import com.warehouse.auth.security.JwtUtil;
import org.springframework.security
    .authentication.AuthenticationManager;
import org.springframework.security
    .authentication
    .UsernamePasswordAuthenticationToken;
import org.springframework.security.core
    .Authentication;
import org.springframework.security.crypto
    .password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    // ✅ Constructor injection
    private final UserCredentialRepository
        repository;
    private final PasswordEncoder
        passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager
        authenticationManager;

    public AuthService(
            UserCredentialRepository repository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager
                authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager =
            authenticationManager;
    }

    public String register(
            RegisterRequest request) {
        if (repository.findByUsername(
                request.getUsername())
                .isPresent()) {
            // ✅ Custom exception
            throw new AuthException(
                "Username already exists!");
        }
        UserCredential user =
            new UserCredential();
        user.setAuthId(
            UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(
            passwordEncoder.encode(
                request.getPassword()));
        user.setRole(request.getRole());
        repository.save(user);
        return "User registered successfully!";
    }

    public AuthResponse login(
            AuthRequest request) {
        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );

        if (authentication.isAuthenticated()) {
            UserCredential user = repository
                .findByUsername(
                    request.getUsername())
                .orElseThrow(() ->
                    new AuthException(
                        "User not found!"));

            String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
            );

            return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole()
            );
        }
        throw new AuthException(
            "Invalid credentials!");
    }

    public boolean validateToken(
            String token) {
        return jwtUtil.isTokenValid(token);
    }

    public String getUsernameFromToken(
            String token) {
        return jwtUtil.extractUsername(token);
    }

    public String getRoleFromToken(
            String token) {
        return jwtUtil.extractRole(token);
    }
}