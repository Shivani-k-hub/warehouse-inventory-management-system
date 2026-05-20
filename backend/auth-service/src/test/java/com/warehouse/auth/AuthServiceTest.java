package com.warehouse.auth;

import com.warehouse.auth.dto.AuthRequest;
import com.warehouse.auth.dto.AuthResponse;
import com.warehouse.auth.dto.RegisterRequest;
import com.warehouse.auth.entity.UserCredential;
import com.warehouse.auth.repository.UserCredentialRepository;
import com.warehouse.auth.security.JwtUtil;
import com.warehouse.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private UserCredential testUser;
    private RegisterRequest registerRequest;
    private AuthRequest authRequest;

    @BeforeEach
    void setUp() {
        testUser = new UserCredential();
        testUser.setAuthId("test-id-123");
        testUser.setUsername("testuser");
        testUser.setPassword("encodedPassword");
        testUser.setRole("ADMIN");

        registerRequest = new RegisterRequest(
            "testuser", "Test@123", "ADMIN");

        authRequest = new AuthRequest(
            "testuser", "Test@123");
    }

    @Test
    void testRegisterSuccess() {
        when(repository.findByUsername("testuser"))
            .thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString()))
            .thenReturn("encodedPassword");
        when(repository.save(any()))
            .thenReturn(testUser);

        String result = authService
            .register(registerRequest);

        assertEquals(
            "User registered successfully!", result);
        verify(repository, times(1)).save(any());
    }

    @Test
    void testRegisterUsernameExists() {
        when(repository.findByUsername("testuser"))
            .thenReturn(Optional.of(testUser));

        RuntimeException ex =
            assertThrows(RuntimeException.class,
                () -> authService
                    .register(registerRequest));

        assertEquals("Username already exists!",
            ex.getMessage());
    }

    @Test
    void testLoginSuccess() {
        Authentication mockAuth =
            mock(Authentication.class);
        when(mockAuth.isAuthenticated())
            .thenReturn(true);
        when(authenticationManager.authenticate(any()))
            .thenReturn(mockAuth);
        when(repository.findByUsername("testuser"))
            .thenReturn(Optional.of(testUser));
        when(jwtUtil.generateToken(
            "testuser", "ADMIN"))
            .thenReturn("mock-jwt-token");

        AuthResponse response =
            authService.login(authRequest);

        assertNotNull(response);
        assertEquals("mock-jwt-token",
            response.getToken());
        assertEquals("testuser",
            response.getUsername());
        assertEquals("ADMIN", response.getRole());
    }

    @Test
    void testLoginNotAuthenticated() {
        Authentication mockAuth =
            mock(Authentication.class);
        when(mockAuth.isAuthenticated())
            .thenReturn(false);
        when(authenticationManager.authenticate(any()))
            .thenReturn(mockAuth);

        RuntimeException ex =
            assertThrows(RuntimeException.class,
                () -> authService.login(authRequest));

        assertEquals("Invalid credentials!",
            ex.getMessage());
    }

    @Test
    void testValidateTokenTrue() {
        when(jwtUtil.isTokenValid("valid-token"))
            .thenReturn(true);

        assertTrue(
            authService.validateToken("valid-token"));
    }

    @Test
    void testValidateTokenFalse() {
        when(jwtUtil.isTokenValid("bad-token"))
            .thenReturn(false);

        assertFalse(
            authService.validateToken("bad-token"));
    }

    @Test
    void testGetUsernameFromToken() {
        when(jwtUtil.extractUsername("token"))
            .thenReturn("testuser");

        assertEquals("testuser",
            authService
                .getUsernameFromToken("token"));
    }

    @Test
    void testGetRoleFromToken() {
        when(jwtUtil.extractRole("token"))
            .thenReturn("ADMIN");

        assertEquals("ADMIN",
            authService.getRoleFromToken("token"));
    }

    @Test
    void testLoginUserNotFound() {
        Authentication mockAuth =
            mock(Authentication.class);
        when(mockAuth.isAuthenticated())
            .thenReturn(true);
        when(authenticationManager.authenticate(any()))
            .thenReturn(mockAuth);
        when(repository.findByUsername("testuser"))
            .thenReturn(Optional.empty());

        RuntimeException ex =
            assertThrows(RuntimeException.class,
                () -> authService.login(authRequest));

        assertEquals("User not found!",
            ex.getMessage());
    }
}