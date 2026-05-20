package com.warehouse.client.service;

import com.warehouse.client.dto.EmployeeDTO;
import com.warehouse.client.dto.ProductDTO;
import com.warehouse.client.dto.WarehouseDTO;
import com.warehouse.client.feign.EmployeeClient;
import com.warehouse.client.feign.ProductClient;
import com.warehouse.client.feign.WarehouseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DashboardService {

    private static final Logger logger =
            LoggerFactory.getLogger(DashboardService.class);

    @Autowired
    private ProductClient productClient;

    @Autowired
    private WarehouseClient warehouseClient;

    @Autowired
    private EmployeeClient employeeClient;

    // Async method - runs in separate thread
    @Async
    public CompletableFuture<List<ProductDTO>> getProductsAsync() {
        logger.info("Fetching products asynchronously - Thread: {}",
                Thread.currentThread().getName());
        List<ProductDTO> products = productClient.getAllProducts();
        return CompletableFuture.completedFuture(products);
    }

    // Async method - runs in separate thread
    @Async
    public CompletableFuture<List<WarehouseDTO>> getWarehousesAsync() {
        logger.info("Fetching warehouses asynchronously - Thread: {}",
                Thread.currentThread().getName());
        List<WarehouseDTO> warehouses = warehouseClient.getAllWarehouses();
        return CompletableFuture.completedFuture(warehouses);
    }

    // Async method - runs in separate thread
    @Async
    public CompletableFuture<List<EmployeeDTO>> getEmployeesAsync() {
        logger.info("Fetching employees asynchronously - Thread: {}",
                Thread.currentThread().getName());
        List<EmployeeDTO> employees = employeeClient.getAllEmployees();
        return CompletableFuture.completedFuture(employees);
    }
}