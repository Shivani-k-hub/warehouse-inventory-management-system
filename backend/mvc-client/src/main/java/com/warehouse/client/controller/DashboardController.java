package com.warehouse.client.controller;

import com.warehouse.client.dto.EmployeeDTO;
import com.warehouse.client.dto.ProductDTO;
import com.warehouse.client.dto.WarehouseDTO;
import com.warehouse.client.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam String token,
                           @RequestParam String username,
                           @RequestParam String role,
                           Model model) {
        try {
            // Call ALL services in PARALLEL using @Async!
            CompletableFuture<List<ProductDTO>> productsFuture =
                    dashboardService.getProductsAsync();

            CompletableFuture<List<WarehouseDTO>> warehousesFuture =
                    dashboardService.getWarehousesAsync();

            CompletableFuture<List<EmployeeDTO>> employeesFuture =
                    dashboardService.getEmployeesAsync();

            // Wait for ALL to complete together
            CompletableFuture.allOf(
                    productsFuture,
                    warehousesFuture,
                    employeesFuture
            ).join();

            // Add results to model
            model.addAttribute("products", productsFuture.get());
            model.addAttribute("warehouses", warehousesFuture.get());
            model.addAttribute("employees", employeesFuture.get());
            model.addAttribute("token", token);
            model.addAttribute("username", username);
            model.addAttribute("role", role);

            // Count totals
            model.addAttribute("totalProducts",
                    productsFuture.get().size());
            model.addAttribute("totalWarehouses",
                    warehousesFuture.get().size());
            model.addAttribute("totalEmployees",
                    employeesFuture.get().size());

        } catch (Exception e) {
            model.addAttribute("error",
                    "Error loading dashboard: " + e.getMessage());
        }
        return "dashboard";
    }
}