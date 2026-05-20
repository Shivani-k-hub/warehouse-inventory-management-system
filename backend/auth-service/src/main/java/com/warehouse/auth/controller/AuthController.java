package com.warehouse.auth.controller;

import com.warehouse.auth.dto.AuthRequest;
import com.warehouse.auth.dto.AuthResponse;
import com.warehouse.auth.dto.RegisterRequest;
import com.warehouse.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation
    .BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller",
     description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody
            RegisterRequest request,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors =
                new StringBuilder();
            result.getAllErrors().forEach(
                error -> errors.append(
                    error.getDefaultMessage())
                    .append(", "));
            return ResponseEntity.badRequest()
                    .body(errors.toString());
        }
        return ResponseEntity.ok(
            authService.register(request));
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    // ✅ Return AuthResponse not String!
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody
            AuthRequest request,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors =
                new StringBuilder();
            result.getAllErrors().forEach(
                error -> errors.append(
                    error.getDefaultMessage())
                    .append(", "));
            return ResponseEntity.badRequest()
                    .body(null);
        }
        // ✅ Returns JSON object!
        return ResponseEntity.ok(
            authService.login(request));
    }

    @Operation(summary = "Register employee")
    @PostMapping("/register/employee")
    public ResponseEntity<String>
            registerEmployee(
                @Valid @RequestBody
                RegisterRequest request,
                BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors =
                new StringBuilder();
            result.getAllErrors().forEach(
                error -> errors.append(
                    error.getDefaultMessage())
                    .append(", "));
            return ResponseEntity.badRequest()
                    .body(errors.toString());
        }

        String role = request.getRole();
        if (!role.equals("ADMIN") &&
            !role.equals("MANAGER") &&
            !role.equals("WORKER")) {
            return ResponseEntity.badRequest()
                .body("Invalid role!");
        }

        String registerResult =
            authService.register(request);
        return ResponseEntity.ok(
            "Employee account created: "
            + registerResult);
    }

    @Operation(summary = "Validate token")
    @GetMapping("/validate")
    public ResponseEntity<Boolean>
            validateToken(
                @RequestParam String token) {
        return ResponseEntity.ok(
            authService.validateToken(token));
    }

    @Operation(summary = "Get username")
    @GetMapping("/username")
    public ResponseEntity<String> getUsername(
            @RequestParam String token) {
        return ResponseEntity.ok(
            authService
                .getUsernameFromToken(token));
    }

    @Operation(summary = "Get role")
    @GetMapping("/role")
    public ResponseEntity<String> getRole(
            @RequestParam String token) {
        return ResponseEntity.ok(
            authService
                .getRoleFromToken(token));
    }

    @Operation(summary = "Health check")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok(
            "Auth Service is running!");
    }
}