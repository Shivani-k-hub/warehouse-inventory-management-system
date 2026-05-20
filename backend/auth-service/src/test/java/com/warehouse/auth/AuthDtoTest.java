package com.warehouse.auth;

import com.warehouse.auth.dto.AuthRequest;
import com.warehouse.auth.dto.AuthResponse;
import com.warehouse.auth.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthDtoTest {

    @Test
    void testAuthRequest() {
        AuthRequest request = new AuthRequest();
        request.setUsername("testuser");
        request.setPassword("Test@123");

        assertEquals("testuser", 
            request.getUsername());
        assertEquals("Test@123", 
            request.getPassword());
    }

    @Test
    void testAuthRequestConstructor() {
        AuthRequest request = new AuthRequest(
            "testuser", "Test@123");

        assertNotNull(request);
        assertEquals("testuser", 
            request.getUsername());
    }

    @Test
    void testAuthResponse() {
        AuthResponse response = new AuthResponse("jwt-token", "testuser", "ADMIN");
        response.setToken("jwt-token");
        response.setUsername("testuser");
        response.setRole("ADMIN");

        assertEquals("jwt-token", 
            response.getToken());
        assertEquals("testuser", 
            response.getUsername());
        assertEquals("ADMIN", 
            response.getRole());
    }

    @Test
    void testAuthResponseConstructor() {
        AuthResponse response = new AuthResponse(
            "jwt-token", "testuser", "ADMIN");

        assertNotNull(response);
        assertEquals("jwt-token", 
            response.getToken());
    }

    @Test
    void testRegisterRequest() {
        RegisterRequest request = 
            new RegisterRequest();
        request.setUsername("testuser");
        request.setPassword("Test@123");
        request.setRole("ADMIN");

        assertEquals("testuser", 
            request.getUsername());
        assertEquals("Test@123", 
            request.getPassword());
        assertEquals("ADMIN", 
            request.getRole());
    }

    @Test
    void testRegisterRequestConstructor() {
        RegisterRequest request = 
            new RegisterRequest(
                "testuser", "Test@123", "ADMIN");

        assertNotNull(request);
        assertEquals("testuser", 
            request.getUsername());
    }
}