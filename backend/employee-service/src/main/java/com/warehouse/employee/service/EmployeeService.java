package com.warehouse.employee.service;

import com.warehouse.employee.entity.Employee;
import com.warehouse.employee.repository
    .EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final Logger logger =
        LoggerFactory.getLogger(
            EmployeeService.class);

    // ✅ Constructor injection
    private final EmployeeRepository repository;

    public EmployeeService(
            EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(
            Employee employee) {
        employee.setEmpId(
            UUID.randomUUID().toString());
        logger.info("Creating employee: {}",
            employee.getEmpName());
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(
            String id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Employee not found!"));
    }

    public Employee updateEmployee(
            String id, Employee employee) {
        employee.setEmpId(id);
        logger.info("Updating employee: {}",
            id);
        return repository.save(employee);
    }

    public String deleteEmployee(String id) {
        repository.deleteById(id);
        logger.info("Deleted employee: {}",
            id);
        return "Employee deleted successfully!";
    }

    public List<Employee>
            getEmployeesByWarehouse(
                String warehouseId) {
        return repository
            .findByWarehouseId(warehouseId);
    }
}