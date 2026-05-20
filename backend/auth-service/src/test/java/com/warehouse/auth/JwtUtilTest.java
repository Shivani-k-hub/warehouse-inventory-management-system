package com.warehouse.auth;

import com.warehouse.auth.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util
    .ReflectionTestUtils;

import static org.junit.jupiter.api
    .Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    // ✅ Same secret as application.properties
    private static final String SECRET =
        "404E635266556A586E3272357538782F" +
        "413F4428472B4B6250645367566B5970";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        // ✅ Inject secret via reflection
        ReflectionTestUtils.setField(
            jwtUtil, "secret", SECRET);
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtil.generateToken(
            "admin1", "ADMIN");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtil.generateToken(
            "admin1", "ADMIN");
        String username =
            jwtUtil.extractUsername(token);
        assertEquals("admin1", username);
    }

    @Test
    void testExtractRole() {
        String token = jwtUtil.generateToken(
            "admin1", "ADMIN");
        String role =
            jwtUtil.extractRole(token);
        assertEquals("ADMIN", role);
    }

    @Test
    void testIsTokenValid() {
        String token = jwtUtil.generateToken(
            "admin1", "ADMIN");
        assertTrue(
            jwtUtil.isTokenValid(token));
    }

    @Test
    void testIsTokenInvalid() {
        assertFalse(
            jwtUtil.isTokenValid(
                "invalid.token.here"));
    }

    @Test
    void testDifferentUsers() {
        String token1 =
            jwtUtil.generateToken(
                "user1", "WORKER");
        String token2 =
            jwtUtil.generateToken(
                "user2", "MANAGER");

        assertEquals("user1",
            jwtUtil.extractUsername(token1));
        assertEquals("user2",
            jwtUtil.extractUsername(token2));
        assertEquals("WORKER",
            jwtUtil.extractRole(token1));
        assertEquals("MANAGER",
            jwtUtil.extractRole(token2));
    }
}