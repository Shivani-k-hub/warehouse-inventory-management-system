package com.warehouse.warehouse;

import com.warehouse.warehouse.aspect.LoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseAspectTest {

    private LoggingAspect loggingAspect;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    @BeforeEach
    void setUp() {
        loggingAspect = new LoggingAspect();
    }

    @Test
    void testLogBeforeService() {
        when(joinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getDeclaringTypeName())
            .thenReturn("WarehouseService");
        when(methodSignature.getName())
            .thenReturn("createWarehouse");

        assertDoesNotThrow(() ->
            loggingAspect.logBeforeService(
                joinPoint));
    }

    @Test
    void testLogBeforeController() {
        when(joinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getDeclaringTypeName())
            .thenReturn("WarehouseController");
        when(methodSignature.getName())
            .thenReturn("getAllWarehouses");

        assertDoesNotThrow(() ->
            loggingAspect.logBeforeController(
                joinPoint));
    }

    @Test
    void testLogExecutionTime() throws Throwable {
        when(proceedingJoinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getName())
            .thenReturn("createWarehouse");
        when(proceedingJoinPoint.proceed())
            .thenReturn("result");

        Object result = loggingAspect
            .logExecutionTime(proceedingJoinPoint);

        assertEquals("result", result);
    }

    @Test
    void testLogAfterException() {
        when(joinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getName())
            .thenReturn("createWarehouse");

        Exception ex =
            new RuntimeException("Test error");

        assertDoesNotThrow(() ->
            loggingAspect.logAfterException(
                joinPoint, ex));
    }
}