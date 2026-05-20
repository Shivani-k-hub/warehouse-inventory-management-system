package com.warehouse.warehouse.service;

import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.repository
    .WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WarehouseService {

    private static final Logger logger =
        LoggerFactory.getLogger(
            WarehouseService.class);

    // ✅ Constructor injection
    private final WarehouseRepository repository;

    public WarehouseService(
            WarehouseRepository repository) {
        this.repository = repository;
    }

    public Warehouse createWarehouse(
            Warehouse warehouse) {
        if (warehouse.getWarehouseId() == null
            || warehouse.getWarehouseId()
                   .isEmpty()) {
            warehouse.setWarehouseId(
                UUID.randomUUID().toString());
        }
        // ✅ Current stock starts at 0
        warehouse.setWarehouseCurrent(0);
        logger.info("Creating warehouse: {}",
            warehouse.getWarehouseName());
        return repository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return repository.findAll();
    }

    public Warehouse getWarehouseById(
            String id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Warehouse not found!"));
    }

    public Warehouse updateWarehouse(
            String id, Warehouse warehouse) {
        warehouse.setWarehouseId(id);
        logger.info("Updating warehouse: {}",
            id);
        return repository.save(warehouse);
    }

    public String deleteWarehouse(String id) {
        repository.deleteById(id);
        logger.info("Deleted warehouse: {}",
            id);
        return "Warehouse deleted successfully!";
    }
    public Warehouse updateStockFromProducts(
            String warehouseId,
            int totalStock) {
        Warehouse warehouse =
            getWarehouseById(warehouseId);
        warehouse.setWarehouseCurrent(
            totalStock);
        return repository.save(warehouse);
    }
}