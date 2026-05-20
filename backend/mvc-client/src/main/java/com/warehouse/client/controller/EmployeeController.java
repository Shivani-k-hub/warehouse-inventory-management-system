package com.warehouse.client.controller;

import com.warehouse.client.dto.EmployeeDTO;
import com.warehouse.client.feign.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping
    public String getAllEmployees(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("employees",
                employeeClient.getAllEmployees());
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployeePage(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute EmployeeDTO employee) {
        employeeClient.createEmployee(employee);
        return "redirect:/employees?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editEmployeePage(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            Model model) {
        EmployeeDTO employee = employeeClient.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "employee-edit";
    }

    // Handle edit form
    @PostMapping("/edit/{id}")
    public String updateEmployee(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role,
            @ModelAttribute EmployeeDTO employee) {
        employeeClient.updateEmployee(id, employee);
        return "redirect:/employees?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(
            @PathVariable String id,
            @RequestParam String token,
            @RequestParam String username,
            @RequestParam String role) {
        employeeClient.deleteEmployee(id);
        return "redirect:/employees?token=" + token
                + "&username=" + username
                + "&role=" + role;
    }
}

