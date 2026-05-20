package com.warehouse.employee.repository;

import com.warehouse.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository 
    extends JpaRepository<Employee, String> {
    
    List<Employee> findByWarehouseId(String warehouseId);
    List<Employee> findByEmpRole(String empRole);
}