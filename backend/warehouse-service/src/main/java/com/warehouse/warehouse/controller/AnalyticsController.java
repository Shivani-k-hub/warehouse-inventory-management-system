package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouses/analytics")
//@CrossOrigin(origins = "*")
@Tag(name = "Analytics",
     description = "Warehouse Analytics APIs")
public class AnalyticsController {

    private final WarehouseService
        warehouseService;

    public AnalyticsController(
            WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Warehouse utilization")
    @GetMapping("/utilization")
    public ResponseEntity<List<Map<String, Object>>>
            getWarehouseUtilization() {

        List<Warehouse> all =
            warehouseService.getAllWarehouses();

        List<Map<String, Object>> analytics =
            new ArrayList<>();

        all.forEach(w -> {
            Map<String, Object> data =
                new HashMap<>();
            data.put("warehouseName",
                w.getWarehouseName());
            data.put("capacity",
                w.getWarehouseCapacity());
            data.put("current",
                w.getWarehouseCurrent());

            double utilization = 0;
            if (w.getWarehouseCapacity() > 0) {
                utilization =
                    (double) w.getWarehouseCurrent()
                    / w.getWarehouseCapacity()
                    * 100;
            }
            data.put("utilizationPercent",
                Math.round(utilization));
            data.put("available",
                w.getWarehouseCapacity()
                - w.getWarehouseCurrent());

            analytics.add(data);
        });

        return ResponseEntity.ok(analytics);
    }

    @Operation(summary = "Warehouse summary")
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>>
            getWarehouseSummary() {

        List<Warehouse> all =
            warehouseService.getAllWarehouses();

        int totalCap = all.stream()
            .mapToInt(
                Warehouse::getWarehouseCapacity)
            .sum();
        int totalCur = all.stream()
            .mapToInt(
                Warehouse::getWarehouseCurrent)
            .sum();

        Map<String, Object> summary =
            new HashMap<>();
        summary.put("totalWarehouses",
            all.size());
        summary.put("totalCapacity", totalCap);
        summary.put("totalCurrent", totalCur);

        double overall = totalCap > 0
            ? (double) totalCur / totalCap * 100
            : 0;
        summary.put("overallUtilization",
            Math.round(overall));

        return ResponseEntity.ok(summary);
    }
}