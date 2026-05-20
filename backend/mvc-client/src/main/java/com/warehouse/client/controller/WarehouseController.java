package com.warehouse.client.controller;

import com.warehouse.client.dto.WarehouseDTO;
import com.warehouse.client.feign.WarehouseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseClient warehouseClient;

    // Get all warehouses
    @GetMapping
    public String getAllWarehouses(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("warehouses",
                warehouseClient.getAllWarehouses());
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "warehouses";
    }

    // Show add form
    @GetMapping("/add")
    public String addWarehousePage(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "warehouse-add";
    }

    // Handle add form
    @PostMapping("/add")
    public String addWarehouse(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute WarehouseDTO warehouse) {
        warehouseClient.createWarehouse(warehouse);
        return "redirect:/warehouses?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editWarehousePage(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        WarehouseDTO warehouse = warehouseClient.getWarehouseById(id);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "warehouse-edit";
    }

    // Handle edit form
    @PostMapping("/edit/{id}")
    public String updateWarehouse(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute WarehouseDTO warehouse) {
        warehouseClient.updateWarehouse(id, warehouse);
        return "redirect:/warehouses?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    // Delete warehouse
    @GetMapping("/delete/{id}")
    public String deleteWarehouse(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role) {
        warehouseClient.deleteWarehouse(id);
        return "redirect:/warehouses?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }
}