package com.warehouse.product.aspect;

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

    @Pointcut("execution(* com.warehouse.product.service.*.*(..))")
    public void productServiceMethods() {}

    @Pointcut("execution(* com.warehouse.product.controller.*.*(..))")
    public void productControllerMethods() {}

    @Before("productServiceMethods()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("==> SERVICE METHOD CALLED: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("productControllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info("==> API REQUEST: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Around("productServiceMethods()")
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
        pointcut = "productServiceMethods()",
        throwing = "exception")
    public void logAfterException(
            JoinPoint joinPoint, Throwable exception) {
        logger.error("==> EXCEPTION IN: {}() → ERROR: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }
}