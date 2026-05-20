package com.warehouse.auth.security;

import com.warehouse.auth.entity.UserCredential;
import com.warehouse.auth.repository
    .UserCredentialRepository;
import org.springframework.security.core
    .userdetails.User;
import org.springframework.security.core
    .userdetails.UserDetails;
import org.springframework.security.core
    .userdetails.UserDetailsService;
import org.springframework.security.core
    .userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    // ✅ Constructor injection
    private final UserCredentialRepository
        repository;

    public CustomUserDetailsService(
            UserCredentialRepository
                repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        UserCredential user = repository
            .findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "User not found: " +
                    username));

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }
}