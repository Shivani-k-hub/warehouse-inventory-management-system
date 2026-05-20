package com.warehouse.employee.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private String empId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_phone")
    private String empPhone;

    @Column(name = "emp_address")
    private String empAddress;

    @Column(name = "emp_role")
    private String empRole;

    @Column(name = "emp_gender")
    private String empGender;

    @Column(name = "emp_city")
    private String empCity;

    @Column(name = "warehouse_id")
    private String warehouseId;

    public Employee() {
        // Default constructor required by JPA
    }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public String getEmpPhone() { return empPhone; }
    public void setEmpPhone(String empPhone) { this.empPhone = empPhone; }
    public String getEmpAddress() { return empAddress; }
    public void setEmpAddress(String empAddress) { this.empAddress = empAddress; }
    public String getEmpRole() { return empRole; }
    public void setEmpRole(String empRole) { this.empRole = empRole; }
    public String getEmpGender() { return empGender; }
    public void setEmpGender(String empGender) { this.empGender = empGender; }
    public String getEmpCity() { return empCity; }
    public void setEmpCity(String empCity) { this.empCity = empCity; }
    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
}