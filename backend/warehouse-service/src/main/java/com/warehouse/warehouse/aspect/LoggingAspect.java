package com.warehouse.warehouse.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.warehouse.warehouse.service.*.*(..))")
    public void warehouseServiceMethods() {}

    @Pointcut("execution(* com.warehouse.warehouse.controller.*.*(..))")
    public void warehouseControllerMethods() {}

    @Before("warehouseServiceMethods()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("==> SERVICE METHOD CALLED: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("warehouseControllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info("==> API REQUEST: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Around("warehouseServiceMethods()")
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("==> METHOD: {}() COMPLETED IN: {} ms",
                joinPoint.getSignature().getName(),
                endTime - startTime);
        return result;
    }

    @AfterThrowing(
        pointcut = "warehouseServiceMethods()",
        throwing = "exception")
    public void logAfterException(
            JoinPoint joinPoint, Throwable exception) {
        logger.error("==> EXCEPTION IN: {}() → ERROR: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }
}