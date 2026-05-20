package com.warehouse.warehouse;

import com.warehouse.warehouse.controller
    .WarehouseController;
import com.warehouse.warehouse.dto.WarehouseDTO;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.service
    .WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers
    .anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseControllerTest {

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController
        warehouseController;

    private Warehouse testWarehouse;
    private WarehouseDTO testDTO;

    @BeforeEach
    void setUp() {
        testWarehouse = new Warehouse();
        testWarehouse.setWarehouseId("wh-123");
        testWarehouse.setWarehouseName(
            "Warehouse A");
        testWarehouse.setWarehouseAddress(
            "123 Main St");
        testWarehouse.setWarehouseCapacity(1000);
        testWarehouse.setWarehouseCurrent(500);
        testWarehouse.setWarehouseDescription(
            "Main warehouse");

        testDTO = new WarehouseDTO();
        testDTO.setWarehouseName("Warehouse A");
        testDTO.setWarehouseAddress(
            "123 Main St");
        testDTO.setWarehouseCapacity(1000);
    }

    @Test
    void testCreateWarehouse() {
        when(warehouseService.createWarehouse(
            any(Warehouse.class)))
            .thenReturn(testWarehouse);

        ResponseEntity<String> response =
            warehouseController
                .createWarehouse(testDTO);

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Warehouse created successfully!",
            response.getBody());
    }

    @Test
    void testGetAllWarehouses() {
        when(warehouseService.getAllWarehouses())
            .thenReturn(
                Arrays.asList(testWarehouse));

        ResponseEntity<List<Warehouse>>
            response = warehouseController
                .getAllWarehouses();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetWarehouseById() {
        when(warehouseService.getWarehouseById(
            "wh-123"))
            .thenReturn(testWarehouse);

        ResponseEntity<Warehouse> response =
            warehouseController
                .getWarehouseById("wh-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("wh-123",
            response.getBody()
                .getWarehouseId());
    }

    @Test
    void testUpdateWarehouse() {
        when(warehouseService.updateWarehouse(
            anyString(), any(Warehouse.class)))
            .thenReturn(testWarehouse);

        ResponseEntity<String> response =
            warehouseController.updateWarehouse(
                "wh-123", testDTO);

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Warehouse updated successfully!",
            response.getBody());
    }

    @Test
    void testDeleteWarehouse() {
        when(warehouseService.deleteWarehouse(
            "wh-123"))
            .thenReturn(
                "Warehouse deleted " +
                "successfully!");

        ResponseEntity<String> response =
            warehouseController
                .deleteWarehouse("wh-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Warehouse deleted successfully!",
            response.getBody());
    }
}