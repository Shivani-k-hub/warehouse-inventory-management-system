package com.warehouse.auth;

import com.warehouse.auth.controller
    .AuthController;
import com.warehouse.auth.dto.AuthRequest;
import com.warehouse.auth.dto.AuthResponse;
import com.warehouse.auth.dto.RegisterRequest;
import com.warehouse.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation
    .BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    private RegisterRequest registerRequest;
    private AuthRequest authRequest;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest(
            "testuser", "Test@123", "ADMIN");
        authRequest = new AuthRequest(
            "testuser", "Test@123");
        authResponse = new AuthResponse(
            "jwt-token", "testuser", "ADMIN");
    }

    @Test
    void testRegisterSuccess() {
        when(bindingResult.hasErrors())
            .thenReturn(false);
        when(authService.register(
            registerRequest))
            .thenReturn(
                "User registered successfully!");

        ResponseEntity<String> response =
            authController.register(
                registerRequest, bindingResult);

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "User registered successfully!",
            response.getBody());
    }

    @Test
    void testRegisterValidationError() {
        when(bindingResult.hasErrors())
            .thenReturn(true);
        when(bindingResult.getAllErrors())
            .thenReturn(
                Collections.emptyList());

        ResponseEntity<String> response =
            authController.register(
                registerRequest, bindingResult);

        assertEquals(400,
            response.getStatusCode().value());
    }

    @Test
    void testLoginSuccess() {
        when(bindingResult.hasErrors())
            .thenReturn(false);
        when(authService.login(authRequest))
            .thenReturn(authResponse);

        ResponseEntity<AuthResponse> response =
            authController.login(
                authRequest, bindingResult);

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testLoginValidationError() {
        when(bindingResult.hasErrors())
            .thenReturn(true);
        when(bindingResult.getAllErrors())
            .thenReturn(
                Collections.emptyList());

        ResponseEntity<AuthResponse> response =
            authController.login(
                authRequest, bindingResult);

        assertEquals(400,
            response.getStatusCode().value());
    }

    @Test
    void testValidateToken() {
        when(authService.validateToken(
            "jwt-token"))
            .thenReturn(true);

        ResponseEntity<Boolean> response =
            authController
                .validateToken("jwt-token");

        assertEquals(200,
            response.getStatusCode().value());
        assertTrue(response.getBody());
    }

    @Test
    void testGetUsername() {
        when(authService.getUsernameFromToken(
            "jwt-token"))
            .thenReturn("testuser");

        ResponseEntity<String> response =
            authController
                .getUsername("jwt-token");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("testuser",
            response.getBody());
    }

    @Test
    void testGetRole() {
        when(authService.getRoleFromToken(
            "jwt-token"))
            .thenReturn("ADMIN");

        ResponseEntity<String> response =
            authController.getRole("jwt-token");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("ADMIN",
            response.getBody());
    }

    @Test
    void testHealthCheck() {
        ResponseEntity<String> response =
            authController.health();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Auth Service is running!",
            response.getBody());
    }

    @Test
    void testRegisterEmployee() {
        when(bindingResult.hasErrors())
            .thenReturn(false);
        when(authService.register(
            registerRequest))
            .thenReturn(
                "User registered successfully!");

        ResponseEntity<String> response =
            authController.registerEmployee(
                registerRequest, bindingResult);

        assertEquals(200,
            response.getStatusCode().value());
    }
}