package com.warehouse.employee;

import com.warehouse.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeEntityTest {

    @Test
    void testDefaultConstructor() {
        Employee emp = new Employee();
        assertNotNull(emp);
    }

    @Test
    void testSetAndGetEmpId() {
        Employee emp = new Employee();
        emp.setEmpId("emp-123");
        assertEquals("emp-123", emp.getEmpId());
    }

    @Test
    void testSetAndGetEmpName() {
        Employee emp = new Employee();
        emp.setEmpName("John Doe");
        assertEquals("John Doe", emp.getEmpName());
    }

    @Test
    void testSetAndGetEmpPhone() {
        Employee emp = new Employee();
        emp.setEmpPhone("9876543210");
        assertEquals("9876543210",
            emp.getEmpPhone());
    }

    @Test
    void testSetAndGetEmpAddress() {
        Employee emp = new Employee();
        emp.setEmpAddress("123 Main St");
        assertEquals("123 Main St",
            emp.getEmpAddress());
    }

    @Test
    void testSetAndGetEmpRole() {
        Employee emp = new Employee();
        emp.setEmpRole("WORKER");
        assertEquals("WORKER", emp.getEmpRole());
    }

    @Test
    void testSetAndGetEmpGender() {
        Employee emp = new Employee();
        emp.setEmpGender("Male");
        assertEquals("Male", emp.getEmpGender());
    }

    @Test
    void testSetAndGetEmpCity() {
        Employee emp = new Employee();
        emp.setEmpCity("Bangalore");
        assertEquals("Bangalore", emp.getEmpCity());
    }

    @Test
    void testSetAndGetWarehouseId() {
        Employee emp = new Employee();
        emp.setWarehouseId("wh-001");
        assertEquals("wh-001",
            emp.getWarehouseId());
    }

    @Test
    void testAllFields() {
        Employee emp = new Employee();
        emp.setEmpId("emp-123");
        emp.setEmpName("John Doe");
        emp.setEmpPhone("9876543210");
        emp.setEmpAddress("123 Main St");
        emp.setEmpRole("WORKER");
        emp.setEmpGender("Male");
        emp.setEmpCity("Bangalore");
        emp.setWarehouseId("wh-001");

        assertAll(
            () -> assertEquals("emp-123",
                    emp.getEmpId()),
            () -> assertEquals("John Doe",
                    emp.getEmpName()),
            () -> assertEquals("9876543210",
                    emp.getEmpPhone()),
            () -> assertEquals("WORKER",
                    emp.getEmpRole()),
            () -> assertEquals("Bangalore",
                    emp.getEmpCity())
        );
    }
}