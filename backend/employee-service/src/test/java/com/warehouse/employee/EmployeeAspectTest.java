package com.warehouse.employee;

import com.warehouse.employee.aspect.LoggingAspect;
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
class EmployeeAspectTest {

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
            .thenReturn("EmployeeService");
        when(methodSignature.getName())
            .thenReturn("createEmployee");

        assertDoesNotThrow(() ->
            loggingAspect.logBeforeService(
                joinPoint));
    }

    @Test
    void testLogBeforeController() {
        when(joinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getDeclaringTypeName())
            .thenReturn("EmployeeController");
        when(methodSignature.getName())
            .thenReturn("getAllEmployees");

        assertDoesNotThrow(() ->
            loggingAspect.logBeforeController(
                joinPoint));
    }

    @Test
    void testLogExecutionTime() throws Throwable {
        when(proceedingJoinPoint.getSignature())
            .thenReturn(methodSignature);
        when(methodSignature.getName())
            .thenReturn("createEmployee");
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
            .thenReturn("createEmployee");

        Exception ex =
            new RuntimeException("Test error");

        assertDoesNotThrow(() ->
            loggingAspect.logAfterException(
                joinPoint, ex));
    }
}