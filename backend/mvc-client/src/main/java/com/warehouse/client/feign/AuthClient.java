package com.warehouse.client.feign;

import com.warehouse.client.dto.AuthRequest;
import com.warehouse.client.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", url = "http://localhost:8080")
public interface AuthClient {

    @PostMapping("/auth/login")
    AuthResponse login(@RequestBody AuthRequest request);

    @PostMapping("/auth/register")
    String register(@RequestBody AuthRequest request);
}