package com.warehouse.product;

import com.warehouse.product.exception
    .GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import static org.junit.jupiter.api
    .Assertions.*;

class ProductExceptionTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException ex =
            new RuntimeException(
                "Product error");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntimeException(ex);

        assertEquals(400,
            response.getStatusCode().value());
        assertEquals("Product error",
            response.getBody().get("message"));
    }

    @Test
    void testHandleGenericException() {
        Exception ex =
            new Exception("Generic error");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleGenericException(ex);

        assertEquals(500,
            response.getStatusCode().value());
        assertEquals("Something went wrong!",
            response.getBody().get("message"));
    }

    @Test
    void testTimestampPresent() {
        RuntimeException ex =
            new RuntimeException("Test");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntimeException(ex);

        assertNotNull(
            response.getBody().get("timestamp"));
    }

    @Test
    void testStatusCodeInBody() {
        RuntimeException ex =
            new RuntimeException("Test");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntimeException(ex);

        assertEquals(400,
            response.getBody().get("status"));
    }
}