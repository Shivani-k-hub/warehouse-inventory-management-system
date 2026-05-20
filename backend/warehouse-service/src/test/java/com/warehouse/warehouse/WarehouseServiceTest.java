package com.warehouse.warehouse;

import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.repository.WarehouseRepository;
import com.warehouse.warehouse.service.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

    @Mock
    private WarehouseRepository repository;

    @InjectMocks
    private WarehouseService warehouseService;

    private Warehouse testWarehouse;

    @BeforeEach
    void setUp() {
        testWarehouse = new Warehouse();
        testWarehouse.setWarehouseId("wh-123");
        testWarehouse.setWarehouseName("Warehouse A");
        testWarehouse.setWarehouseAddress(
            "123 Main St");
        testWarehouse.setWarehouseCapacity(1000);
        testWarehouse.setWarehouseCurrent(500);
        testWarehouse.setWarehouseDescription(
            "Main warehouse");
    }

    @Test
    void testCreateWarehouse() {
        when(repository.save(any(Warehouse.class)))
            .thenReturn(testWarehouse);

        Warehouse result = warehouseService
            .createWarehouse(testWarehouse);

        assertNotNull(result);
        assertEquals("Warehouse A",
            result.getWarehouseName());
        verify(repository, times(1))
            .save(any(Warehouse.class));
    }

    @Test
    void testGetAllWarehouses() {
        when(repository.findAll())
            .thenReturn(Arrays.asList(testWarehouse));

        List<Warehouse> result =
            warehouseService.getAllWarehouses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Warehouse A",
            result.get(0).getWarehouseName());
    }

    @Test
    void testGetWarehouseById() {
        when(repository.findById("wh-123"))
            .thenReturn(Optional.of(testWarehouse));

        Warehouse result = warehouseService
            .getWarehouseById("wh-123");

        assertNotNull(result);
        assertEquals("wh-123",
            result.getWarehouseId());
    }

    @Test
    void testGetWarehouseByIdNotFound() {
        when(repository.findById("wrong-id"))
            .thenReturn(Optional.empty());

        RuntimeException exception =
            assertThrows(RuntimeException.class,
                () -> warehouseService
                    .getWarehouseById("wrong-id"));

        assertEquals("Warehouse not found!",
            exception.getMessage());
    }

    @Test
    void testUpdateWarehouse() {
        when(repository.save(any(Warehouse.class)))
            .thenReturn(testWarehouse);

        Warehouse result = warehouseService
            .updateWarehouse("wh-123", testWarehouse);

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Warehouse.class));
    }

    @Test
    void testDeleteWarehouse() {
        doNothing().when(repository)
            .deleteById("wh-123");

        String result = warehouseService
            .deleteWarehouse("wh-123");

        assertEquals(
            "Warehouse deleted successfully!", result);
        verify(repository, times(1))
            .deleteById("wh-123");
    }
}