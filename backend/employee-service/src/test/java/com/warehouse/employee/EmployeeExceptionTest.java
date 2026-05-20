package com.warehouse.employee;

import com.warehouse.employee.exception
    .GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeExceptionTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException ex =
            new RuntimeException("Test error");

        ResponseEntity<Map<String, Object>>
            response = handler
                .handleRuntimeException(ex);

        assertEquals(400,
            response.getStatusCode().value());
        assertEquals("Test error",
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
}