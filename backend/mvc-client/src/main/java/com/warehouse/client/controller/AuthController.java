package com.warehouse.client.controller;

import com.warehouse.client.dto.AuthRequest;
import com.warehouse.client.dto.AuthResponse;
import com.warehouse.client.feign.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthClient authClient;

    // Show login page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // Handle login form
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            AuthRequest request = new AuthRequest(username, password);
            AuthResponse response = authClient.login(request);

            // Store token and user info in model
            model.addAttribute("token", response.getToken());
            model.addAttribute("username", response.getUsername());
            model.addAttribute("role", response.getRole());

            return "redirect:/dashboard?token=" 
                    + response.getToken() 
                    + "&username=" + response.getUsername()
                    + "&role=" + response.getRole();

        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    // Logout
    @RequestMapping(value = "/logout", method = {
        RequestMethod.GET, RequestMethod.POST})
    public String logout() {
        return "redirect:/";
    }
}