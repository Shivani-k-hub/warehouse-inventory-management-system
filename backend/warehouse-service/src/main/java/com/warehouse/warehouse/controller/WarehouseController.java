package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.WarehouseDTO;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.service
    .WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
//@CrossOrigin(origins = "*")
@Tag(name = "Warehouse Controller",
     description = "Warehouse CRUD APIs")
public class WarehouseController {

    // ✅ Constructor injection
    private final WarehouseService
        warehouseService;

    public WarehouseController(
            WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Create warehouse")
    @PostMapping
    // ✅ DTO instead of Entity
    public ResponseEntity<String>
            createWarehouse(
                @RequestBody
                WarehouseDTO dto) {
        Warehouse warehouse = toEntity(dto);
        warehouseService
            .createWarehouse(warehouse);
        return ResponseEntity.ok(
            "Warehouse created successfully!");
    }

    @Operation(summary = "Get all warehouses")
    @GetMapping
    public ResponseEntity<List<Warehouse>>
            getAllWarehouses() {
        return ResponseEntity.ok(
            warehouseService
                .getAllWarehouses());
    }

    @Operation(summary = "Get warehouse by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse>
            getWarehouseById(
                @PathVariable String id) {
        return ResponseEntity.ok(
            warehouseService
                .getWarehouseById(id));
    }

    @Operation(summary = "Update warehouse")
    @PutMapping("/{id}")
    // ✅ DTO instead of Entity
    public ResponseEntity<String>
            updateWarehouse(
                @PathVariable String id,
                @RequestBody WarehouseDTO dto) {
        Warehouse warehouse = toEntity(dto);
        warehouseService.updateWarehouse(
            id, warehouse);
        return ResponseEntity.ok(
            "Warehouse updated successfully!");
    }

    @Operation(summary = "Delete warehouse")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
            deleteWarehouse(
                @PathVariable String id) {
        return ResponseEntity.ok(
            warehouseService
                .deleteWarehouse(id));
    }

    private Warehouse toEntity(WarehouseDTO dto) {
        Warehouse w = new Warehouse();
        if (dto.getWarehouseId() != null
            && !dto.getWarehouseId().isEmpty()) {
            w.setWarehouseId(
                dto.getWarehouseId());
        }
        w.setWarehouseName(
            dto.getWarehouseName());
        w.setWarehouseAddress(
            dto.getWarehouseAddress());
        w.setWarehouseCapacity(
            dto.getWarehouseCapacity());
        return w;
    }
    
    @Operation(summary = "Update warehouse stock")
    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(
            @PathVariable String id,
            @RequestParam int stock) {
        Warehouse w =
            warehouseService.getWarehouseById(id);
        w.setWarehouseCurrent(stock);
        warehouseService.updateWarehouse(id, w);
        return ResponseEntity.ok(
            "Stock updated successfully!");
    }
}