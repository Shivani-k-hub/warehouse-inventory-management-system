package com.warehouse.employee;

import com.warehouse.employee.entity.Employee;
import com.warehouse.employee.repository.EmployeeRepository;
import com.warehouse.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee testEmployee;

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
    }

    @Test
    void testCreateEmployee() {
        when(repository.save(any(Employee.class)))
            .thenReturn(testEmployee);

        Employee result = employeeService
            .createEmployee(testEmployee);

        assertNotNull(result);
        assertEquals("John Doe",
            result.getEmpName());
        verify(repository, times(1))
            .save(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() {
        when(repository.findAll())
            .thenReturn(Arrays.asList(testEmployee));

        List<Employee> result =
            employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe",
            result.get(0).getEmpName());
    }

    @Test
    void testGetEmployeeById() {
        when(repository.findById("emp-123"))
            .thenReturn(Optional.of(testEmployee));

        Employee result = employeeService
            .getEmployeeById("emp-123");

        assertNotNull(result);
        assertEquals("emp-123",
            result.getEmpId());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(repository.findById("wrong-id"))
            .thenReturn(Optional.empty());

        RuntimeException exception =
            assertThrows(RuntimeException.class,
                () -> employeeService
                    .getEmployeeById("wrong-id"));

        assertEquals("Employee not found!",
            exception.getMessage());
    }

    @Test
    void testUpdateEmployee() {
        when(repository.save(any(Employee.class)))
            .thenReturn(testEmployee);

        Employee result = employeeService
            .updateEmployee("emp-123", testEmployee);

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(repository)
            .deleteById("emp-123");

        String result = employeeService
            .deleteEmployee("emp-123");

        assertEquals(
            "Employee deleted successfully!", result);
        verify(repository, times(1))
            .deleteById("emp-123");
    }

    @Test
    void testGetEmployeesByWarehouse() {
        when(repository.findByWarehouseId("wh-001"))
            .thenReturn(Arrays.asList(testEmployee));

        List<Employee> result = employeeService
            .getEmployeesByWarehouse("wh-001");

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}