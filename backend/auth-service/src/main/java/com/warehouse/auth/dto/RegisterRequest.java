package com.warehouse.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Username required!")
    private String username;

    @NotBlank(message = "Password required!")
    @Size(min = 6,
          message = "Password must be " +
                    "at least 6 characters!")
    @Pattern(
        // ✅ Fixed regex using \\d
        regexp = "^(?=.*[A-Z])(?=.*[a-z])" +
                 "(?=.*\\d)" +
                 "(?=.*[@#$%^&+=!]).*$",
        message = "Password must contain " +
                  "at least one uppercase, " +
                  "one lowercase, one digit, " +
                  "and one special character " +
                  "(@#$%^&+=!)")
    private String password;

    @NotBlank(message = "Role required!")
    private String role;

    // Constructor
    public RegisterRequest() {}

    public RegisterRequest(
            String username,
            String password,
            String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username; }
    public void setUsername(
            String username) {
        this.username = username; }

    public String getPassword() {
        return password; }
    public void setPassword(
            String password) {
        this.password = password; }

    public String getRole() {
        return role; }
    public void setRole(String role) {
        this.role = role; }
}