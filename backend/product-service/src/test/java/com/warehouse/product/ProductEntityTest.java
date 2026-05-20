package com.warehouse.product;

import com.warehouse.product.entity.Product;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api
    .Assertions.*;

class ProductEntityTest {

    @Test
    void testDefaultConstructor() {
        Product p = new Product();
        assertNotNull(p);
    }

    @Test
    void testSetAndGetProductId() {
        Product p = new Product();
        p.setProductId("prod-123");
        assertEquals("prod-123",
            p.getProductId());
    }

    @Test
    void testSetAndGetProductName() {
        Product p = new Product();
        p.setProductName("Rice Bag");
        assertEquals("Rice Bag",
            p.getProductName());
    }

    @Test
    void testSetAndGetProductType() {
        Product p = new Product();
        p.setProductType("Food");
        assertEquals("Food",
            p.getProductType());
    }

    @Test
    void testSetAndGetProductWeight() {
        Product p = new Product();
        p.setProductWeight(25.5);
        assertEquals(25.5,
            p.getProductWeight());
    }

    @Test
    void testSetAndGetProductQuantity() {
        Product p = new Product();
        p.setProductQuantity(100);
        assertEquals(100,
            p.getProductQuantity());
    }

    @Test
    void testSetAndGetProductLocation() {
        Product p = new Product();
        p.setProductLocation("Rack A1");
        assertEquals("Rack A1",
            p.getProductLocation());
    }

    @Test
    void testSetAndGetProductStatus() {
        Product p = new Product();
        p.setProductStatus("AVAILABLE");
        assertEquals("AVAILABLE",
            p.getProductStatus());
    }

    @Test
    void testSetAndGetWarehouseId() {
        Product p = new Product();
        p.setWarehouseId("wh-001");
        assertEquals("wh-001",
            p.getWarehouseId());
    }

    @Test
    void testSetAndGetProductManufacture() {
        Product p = new Product();
        Date date = new Date();
        p.setProductManufacture(date);
        assertEquals(date,
            p.getProductManufacture());
    }

    @Test
    void testSetAndGetProductExpiry() {
        Product p = new Product();
        Date date = new Date();
        p.setProductExpiry(date);
        assertEquals(date,
            p.getProductExpiry());
    }

    @Test
    void testSetAndGetNotifyDaysBefore() {
        Product p = new Product();
        p.setNotifyDaysBefore(3);
        assertEquals(3,
            p.getNotifyDaysBefore());
    }

    @Test
    void testSetAndGetLastUpdated() {
        Product p = new Product();
        Date date = new Date();
        p.setProductLastUpdated(date);
        assertEquals(date,
            p.getProductLastUpdated());
    }

    @Test
    void testAllFieldsTogether() {
        Product p = new Product();
        p.setProductId("prod-123");
        p.setProductName("Rice Bag");
        p.setProductType("Food");
        p.setProductWeight(25.5);
        p.setProductQuantity(100);
        p.setProductStatus("AVAILABLE");
        p.setProductLocation("Rack A1");
        p.setWarehouseId("wh-001");

        assertAll(
            () -> assertEquals("prod-123",
                p.getProductId()),
            () -> assertEquals("Rice Bag",
                p.getProductName()),
            () -> assertEquals("Food",
                p.getProductType()),
            () -> assertEquals(25.5,
                p.getProductWeight()),
            () -> assertEquals(100,
                p.getProductQuantity()),
            () -> assertEquals("AVAILABLE",
                p.getProductStatus())
        );
    }
}