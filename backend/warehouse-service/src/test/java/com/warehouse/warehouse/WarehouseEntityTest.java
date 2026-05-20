package com.warehouse.warehouse;

import com.warehouse.warehouse.entity.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseEntityTest {

    @Test
    void testDefaultConstructor() {
        Warehouse w = new Warehouse();
        assertNotNull(w);
    }

    @Test
    void testSetAndGetWarehouseId() {
        Warehouse w = new Warehouse();
        w.setWarehouseId("wh-123");
        assertEquals("wh-123",
            w.getWarehouseId());
    }

    @Test
    void testSetAndGetWarehouseName() {
        Warehouse w = new Warehouse();
        w.setWarehouseName("Warehouse A");
        assertEquals("Warehouse A",
            w.getWarehouseName());
    }

    @Test
    void testSetAndGetWarehouseAddress() {
        Warehouse w = new Warehouse();
        w.setWarehouseAddress("123 Main St");
        assertEquals("123 Main St",
            w.getWarehouseAddress());
    }

    @Test
    void testSetAndGetWarehouseCapacity() {
        Warehouse w = new Warehouse();
        w.setWarehouseCapacity(1000);
        assertEquals(1000,
            w.getWarehouseCapacity());
    }

    @Test
    void testSetAndGetWarehouseCurrent() {
        Warehouse w = new Warehouse();
        w.setWarehouseCurrent(500);
        assertEquals(500,
            w.getWarehouseCurrent());
    }

    @Test
    void testSetAndGetWarehouseDescription() {
        Warehouse w = new Warehouse();
        w.setWarehouseDescription("Main");
        assertEquals("Main",
            w.getWarehouseDescription());
    }

    @Test
    void testAllFields() {
        Warehouse w = new Warehouse();
        w.setWarehouseId("wh-123");
        w.setWarehouseName("Warehouse A");
        w.setWarehouseAddress("123 Main St");
        w.setWarehouseCapacity(1000);
        w.setWarehouseCurrent(500);
        w.setWarehouseDescription("Main");

        assertAll(
            () -> assertEquals("wh-123",
                    w.getWarehouseId()),
            () -> assertEquals("Warehouse A",
                    w.getWarehouseName()),
            () -> assertEquals("123 Main St",
                    w.getWarehouseAddress()),
            () -> assertEquals(1000,
                    w.getWarehouseCapacity()),
            () -> assertEquals(500,
                    w.getWarehouseCurrent())
        );
    }
}