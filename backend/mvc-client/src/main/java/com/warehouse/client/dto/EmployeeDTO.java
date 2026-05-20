package com.warehouse.client.dto;

public class EmployeeDTO {
    private String empId;
    private String empName;
    private String empPhone;
    private String empAddress;
    private String empRole;
    private String empGender;
    private String empCity;
    private String warehouseId;

    public EmployeeDTO() {}

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