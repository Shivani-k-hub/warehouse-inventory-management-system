package com.warehouse.order;

import com.warehouse.order.exception
    .GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import static org.junit.jupiter.api
    .Assertions.*;

class OrderExceptionTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException ex =
            new RuntimeException("Order error");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntime(ex);

        assertEquals(400,
            response.getStatusCode().value());
        assertEquals("Order error",
            response.getBody().get("message"));
    }

    @Test
    void testHandleGenericException() {
        Exception ex =
            new Exception("Generic error");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleGeneric(ex);

        assertEquals(500,
            response.getStatusCode().value());
        assertEquals("Something went wrong!",
            response.getBody().get("message"));
    }

    @Test
    void testTimestampIncluded() {
        RuntimeException ex =
            new RuntimeException("Test");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntime(ex);

        assertNotNull(
            response.getBody().get("timestamp"));
    }
}