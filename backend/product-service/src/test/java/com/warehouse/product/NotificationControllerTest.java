package com.warehouse.product;

import com.warehouse.product.controller.NotificationController;
import com.warehouse.product.entity.Product;
import com.warehouse.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private NotificationController
        notificationController;

    private Product availableProduct;
    private Product lowStockProduct;
    private Product expiringProduct;
    private Product expiredProduct;

    @BeforeEach
    void setUp() {
        availableProduct = new Product();
        availableProduct.setProductId("p-001");
        availableProduct.setProductName(
            "Normal Product");
        availableProduct.setProductQuantity(100);

        lowStockProduct = new Product();
        lowStockProduct.setProductId("p-002");
        lowStockProduct.setProductName(
            "Low Stock Item");
        lowStockProduct.setProductQuantity(5);

        expiringProduct = new Product();
        expiringProduct.setProductId("p-003");
        expiringProduct.setProductName(
            "Expiring Product");
        expiringProduct.setProductQuantity(50);
        expiringProduct.setNotifyDaysBefore(3);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        expiringProduct.setProductExpiry(
            cal.getTime());

        expiredProduct = new Product();
        expiredProduct.setProductId("p-004");
        expiredProduct.setProductName(
            "Expired Product");
        expiredProduct.setProductQuantity(30);
        expiredProduct.setNotifyDaysBefore(3);

        Calendar pastCal =
            Calendar.getInstance();
        pastCal.add(
            Calendar.DAY_OF_MONTH, -5);
        expiredProduct.setProductExpiry(
            pastCal.getTime());
    }

    @Test
    void testGetNotificationsEmpty() {
        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(
                    availableProduct));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testGetNotificationsLowStock() {
        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(
                    lowStockProduct));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertFalse(
            response.getBody().isEmpty());
        assertEquals("LOW_STOCK",
            response.getBody()
                .get(0).get("type"));
    }

    @Test
    void testGetNotificationsExpiringSoon() {
        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(
                    expiringProduct));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertFalse(
            response.getBody().isEmpty());
        assertEquals("EXPIRING_SOON",
            response.getBody()
                .get(0).get("type"));
    }

    @Test
    void testGetNotificationsExpired() {
        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(
                    expiredProduct));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertFalse(
            response.getBody().isEmpty());
        assertEquals("EXPIRED",
            response.getBody()
                .get(0).get("type"));
    }

    @Test
    void testGetNotificationsMultiple() {
        when(productService.getAllProducts())
            .thenReturn(Arrays.asList(
                lowStockProduct,
                expiredProduct,
                expiringProduct));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(3,
            response.getBody().size());
    }

    @Test
    void testGetNotificationsNoExpiry() {
        Product noExpiry = new Product();
        noExpiry.setProductId("p-005");
        noExpiry.setProductName("No Expiry");
        noExpiry.setProductQuantity(100);
        noExpiry.setProductExpiry(null);

        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(noExpiry));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testDefaultNotifyDays() {
        Product product = new Product();
        product.setProductId("p-006");
        product.setProductName("Test");
        product.setProductQuantity(100);
        product.setNotifyDaysBefore(null);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        product.setProductExpiry(cal.getTime());

        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(product));

        ResponseEntity<List<Map<String, Object>>>
            response = notificationController
                .getNotifications();

        assertEquals(200,
            response.getStatusCode().value());
        assertFalse(
            response.getBody().isEmpty());
    }
}