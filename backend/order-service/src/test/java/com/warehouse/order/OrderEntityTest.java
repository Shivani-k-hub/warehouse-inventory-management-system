package com.warehouse.order;

import com.warehouse.order.entity.Order;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api
    .Assertions.*;

class OrderEntityTest {

    @Test
    void testDefaultConstructor() {
        Order order = new Order();
        assertNotNull(order);
    }

    @Test
    void testSetAndGetOrderId() {
        Order order = new Order();
        order.setOrderId("ord-123");
        assertEquals("ord-123",
            order.getOrderId());
    }

    @Test
    void testSetAndGetProductId() {
        Order order = new Order();
        order.setProductId("prod-123");
        assertEquals("prod-123",
            order.getProductId());
    }

    @Test
    void testSetAndGetProductName() {
        Order order = new Order();
        order.setProductName("Rice Bag");
        assertEquals("Rice Bag",
            order.getProductName());
    }

    @Test
    void testSetAndGetWarehouseId() {
        Order order = new Order();
        order.setWarehouseId("wh-001");
        assertEquals("wh-001",
            order.getWarehouseId());
    }

    @Test
    void testSetAndGetEmployeeId() {
        Order order = new Order();
        order.setEmployeeId("emp-001");
        assertEquals("emp-001",
            order.getEmployeeId());
    }

    @Test
    void testSetAndGetQuantity() {
        Order order = new Order();
        order.setQuantity(10);
        assertEquals(10, order.getQuantity());
    }

    @Test
    void testSetAndGetOrderStatus() {
        Order order = new Order();
        order.setOrderStatus("PENDING");
        assertEquals("PENDING",
            order.getOrderStatus());
    }

    @Test
    void testSetAndGetOrderDate() {
        Order order = new Order();
        Date now = new Date();
        order.setOrderDate(now);
        assertEquals(now, order.getOrderDate());
    }

    @Test
    void testSetAndGetDispatchDate() {
        Order order = new Order();
        Date now = new Date();
        order.setDispatchDate(now);
        assertEquals(now,
            order.getDispatchDate());
    }

    @Test
    void testSetAndGetNotes() {
        Order order = new Order();
        order.setNotes("Urgent delivery");
        assertEquals("Urgent delivery",
            order.getNotes());
    }

    @Test
    void testAllFields() {
        Order order = new Order();
        order.setOrderId("ord-123");
        order.setProductId("prod-123");
        order.setProductName("Rice");
        order.setWarehouseId("wh-001");
        order.setQuantity(5);
        order.setOrderStatus("PENDING");

        assertAll(
            () -> assertEquals("ord-123",
                order.getOrderId()),
            () -> assertEquals("prod-123",
                order.getProductId()),
            () -> assertEquals("Rice",
                order.getProductName()),
            () -> assertEquals(5,
                order.getQuantity()),
            () -> assertEquals("PENDING",
                order.getOrderStatus())
        );
    }
}