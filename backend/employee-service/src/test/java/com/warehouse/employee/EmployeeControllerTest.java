package com.warehouse.employee;

import com.warehouse.employee.controller
    .EmployeeController;
import com.warehouse.employee.dto.EmployeeDTO;
import com.warehouse.employee.entity.Employee;
import com.warehouse.employee.service
    .EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers
    .anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController
        employeeController;

    private Employee testEmployee;
    private EmployeeDTO testDTO;

    @BeforeEach
    void setUp() {
        testEmployee = new Employee();
        testEmployee.setEmpId("emp-123");
        testEmployee.setEmpName("John Doe");
        testEmployee.setEmpPhone("9876543210");
        testEmployee.setEmpRole("WORKER");
        testEmployee.setEmpCity("Bangalore");
        testEmployee.setEmpGender("Male");
        testEmployee.setWarehouseId("wh-001");

        testDTO = new EmployeeDTO();
        testDTO.setEmpName("John Doe");
        testDTO.setEmpPhone("9876543210");
        testDTO.setEmpRole("WORKER");
        testDTO.setEmpCity("Bangalore");
        testDTO.setEmpGender("Male");
        testDTO.setWarehouseId("wh-001");
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.createEmployee(
            any(Employee.class)))
            .thenReturn(testEmployee);

        ResponseEntity<String> response =
            employeeController
                .createEmployee(testDTO);

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Employee created successfully!",
            response.getBody());
    }

    @Test
    void testGetAllEmployees() {
        when(employeeService.getAllEmployees())
            .thenReturn(
                Arrays.asList(testEmployee));

        ResponseEntity<List<Employee>>
            response = employeeController
                .getAllEmployees();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(
            "emp-123"))
            .thenReturn(testEmployee);

        ResponseEntity<Employee> response =
            employeeController
                .getEmployeeById("emp-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("emp-123",
            response.getBody().getEmpId());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(
            anyString(), any(Employee.class)))
            .thenReturn(testEmployee);

        ResponseEntity<String> response =
            employeeController.updateEmployee(
                "emp-123", testDTO);

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Employee updated successfully!",
            response.getBody());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeService.deleteEmployee(
            "emp-123"))
            .thenReturn(
                "Employee deleted " +
                "successfully!");

        ResponseEntity<String> response =
            employeeController
                .deleteEmployee("emp-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Employee deleted successfully!",
            response.getBody());
    }

    @Test
    void testGetByWarehouse() {
        when(employeeService
            .getEmployeesByWarehouse("wh-001"))
            .thenReturn(
                Arrays.asList(testEmployee));

        ResponseEntity<List<Employee>>
            response = employeeController
                .getByWarehouse("wh-001");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }
}