package com.warehouse.auth;

import com.warehouse.auth.entity.UserCredential;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params
    .ParameterizedTest;
import org.junit.jupiter.params.provider
    .CsvSource;

import static org.junit.jupiter.api
    .Assertions.*;

class UserCredentialTest {

    @Test
    void testDefaultConstructor() {
        UserCredential user =
            new UserCredential();
        assertNotNull(user);
    }

    // ✅ Parameterized test - replaces 3!
    @ParameterizedTest
    @CsvSource({
        "ADMIN",
        "MANAGER",
        "WORKER"
    })
    void testSetRole(String role) {
        UserCredential user =
            new UserCredential();
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    void testSetAndGetAuthId() {
        UserCredential user =
            new UserCredential();
        user.setAuthId("auth-123");
        assertEquals("auth-123",
            user.getAuthId());
    }

    @Test
    void testSetAndGetUsername() {
        UserCredential user =
            new UserCredential();
        user.setUsername("testuser");
        assertEquals("testuser",
            user.getUsername());
    }

    @Test
    void testSetAndGetPassword() {
        UserCredential user =
            new UserCredential();
        user.setPassword("Test@123");
        assertEquals("Test@123",
            user.getPassword());
    }

    @Test
    void testAllFieldsTogether() {
        UserCredential user =
            new UserCredential();
        user.setAuthId("auth-456");
        user.setUsername("admin1");
        user.setPassword("encoded@Pass1");
        user.setRole("ADMIN");

        assertAll(
            () -> assertEquals("auth-456",
                user.getAuthId()),
            () -> assertEquals("admin1",
                user.getUsername()),
            () -> assertEquals(
                "encoded@Pass1",
                user.getPassword()),
            () -> assertEquals("ADMIN",
                user.getRole())
        );
    }
}