package com.warehouse.auth.aspect;

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

    // Pointcut → which methods to intercept
    @Pointcut("execution(* com.warehouse.auth.service.*.*(..))")
    public void authServiceMethods() {}

    @Pointcut("execution(* com.warehouse.auth.controller.*.*(..))")
    public void authControllerMethods() {}

    // Before every service method
    @Before("authServiceMethods()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("==> SERVICE METHOD CALLED: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    // Before every controller method
    @Before("authControllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info("==> API REQUEST: {}.{}()",
                joinPoint.getSignature()
                         .getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    // Around → measures execution time
    @Around("authServiceMethods()")
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // execute method

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info("==> METHOD: {}() COMPLETED IN: {} ms",
                joinPoint.getSignature().getName(),
                duration);

        return result;
    }

    // After exception
    @AfterThrowing(
        pointcut = "authServiceMethods()",
        throwing = "exception")
    public void logAfterException(
            JoinPoint joinPoint, Throwable exception) {
        logger.error("==> EXCEPTION IN: {}() → ERROR: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }
}