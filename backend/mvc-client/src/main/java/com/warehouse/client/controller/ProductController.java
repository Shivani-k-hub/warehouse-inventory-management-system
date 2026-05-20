package com.warehouse.client.controller;

import com.warehouse.client.dto.ProductDTO;
import com.warehouse.client.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductClient productClient;

    @GetMapping
    public String getAllProducts(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("products",
                productClient.getAllProducts());
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "products";
    }

    @GetMapping("/add")
    public String addProductPage(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute ProductDTO product) {
        productClient.createProduct(product);
        return "redirect:/products?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editProductPage(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        ProductDTO product = productClient.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "product-edit";
    }

    // Handle edit form
    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute ProductDTO product) {
        productClient.updateProduct(id, product);
        return "redirect:/products?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role) {
        productClient.deleteProduct(id);
        return "redirect:/products?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }
}