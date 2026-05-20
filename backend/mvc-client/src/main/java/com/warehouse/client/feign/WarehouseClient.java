package com.warehouse.client.feign;

import com.warehouse.client.dto.WarehouseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "warehouse-service", url = "http://localhost:8080")
public interface WarehouseClient {

    @GetMapping("/warehouses")
    List<WarehouseDTO> getAllWarehouses();

    @GetMapping("/warehouses/{id}")
    WarehouseDTO getWarehouseById(@PathVariable String id);

    @PostMapping("/warehouses")
    WarehouseDTO createWarehouse(@RequestBody WarehouseDTO warehouse);

    @PutMapping("/warehouses/{id}")
    WarehouseDTO updateWarehouse(@PathVariable String id,
                                  @RequestBody WarehouseDTO warehouse);

    @DeleteMapping("/warehouses/{id}")
    String deleteWarehouse(@PathVariable String id);
}