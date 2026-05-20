package com.warehouse.client.feign;

import com.warehouse.client.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "employee-service", url = "http://localhost:8080")
public interface EmployeeClient {

    @GetMapping("/employees")
    List<EmployeeDTO> getAllEmployees();

    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable String id);

    @PostMapping("/employees")
    EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee);

    @PutMapping("/employees/{id}")
    EmployeeDTO updateEmployee(@PathVariable String id,
                               @RequestBody EmployeeDTO employee);

    @DeleteMapping("/employees/{id}")
    String deleteEmployee(@PathVariable String id);
}