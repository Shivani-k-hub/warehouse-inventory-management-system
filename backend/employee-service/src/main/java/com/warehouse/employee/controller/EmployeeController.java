package com.warehouse.employee.controller;

import com.warehouse.employee.dto.EmployeeDTO;
import com.warehouse.employee.entity.Employee;
import com.warehouse.employee.service
    .EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
//@CrossOrigin(origins = "*")
@Tag(name = "Employee Controller",
     description = "Employee CRUD APIs")
public class EmployeeController {

    // ✅ Constructor injection
    private final EmployeeService
        employeeService;

    public EmployeeController(
            EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Create employee")
    @PostMapping
    public ResponseEntity<String>
            createEmployee(
                @RequestBody EmployeeDTO dto) {
        Employee emp = toEntity(dto);
        employeeService.createEmployee(emp);
        return ResponseEntity.ok(
            "Employee created successfully!");
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<Employee>>
            getAllEmployees() {
        return ResponseEntity.ok(
            employeeService.getAllEmployees());
    }

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee>
            getEmployeeById(
                @PathVariable String id) {
        return ResponseEntity.ok(
            employeeService
                .getEmployeeById(id));
    }

    @Operation(summary = "Update employee")
    @PutMapping("/{id}")
    public ResponseEntity<String>
            updateEmployee(
                @PathVariable String id,
                @RequestBody EmployeeDTO dto) {
        Employee emp = toEntity(dto);
        employeeService.updateEmployee(id, emp);
        return ResponseEntity.ok(
            "Employee updated successfully!");
    }

    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
            deleteEmployee(
                @PathVariable String id) {
        return ResponseEntity.ok(
            employeeService
                .deleteEmployee(id));
    }

    @Operation(summary = "Get by warehouse")
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Employee>>
            getByWarehouse(
                @PathVariable
                String warehouseId) {
        return ResponseEntity.ok(
            employeeService
                .getEmployeesByWarehouse(
                    warehouseId));
    }

    private Employee toEntity(
            EmployeeDTO dto) {
        Employee e = new Employee();
        e.setEmpName(dto.getEmpName());
        e.setEmpPhone(dto.getEmpPhone());
        e.setEmpAddress(dto.getEmpAddress());
        e.setEmpRole(dto.getEmpRole());
        e.setEmpGender(dto.getEmpGender());
        e.setEmpCity(dto.getEmpCity());
        e.setWarehouseId(dto.getWarehouseId());
        return e;
    }
}