package com.warehouse.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation
    .ExceptionHandler;
import org.springframework.web.bind.annotation
    .RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Constants
    private static final String STATUS =
        "status";
    private static final String MESSAGE =
        "message";
    private static final String TIMESTAMP =
        "timestamp";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>>
            handleRuntimeException(
                RuntimeException ex) {
        Map<String, Object> error =
            new HashMap<>();
        error.put(STATUS,
            HttpStatus.BAD_REQUEST.value());
        error.put(MESSAGE, ex.getMessage());
        error.put(TIMESTAMP,
            LocalDateTime.now().toString());
        return ResponseEntity.badRequest()
            .body(error);
    }

    @ExceptionHandler(
        jakarta.persistence
            .EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
            handleNotFoundException(
                Exception ex) {
        Map<String, Object> error =
            new HashMap<>();
        error.put(STATUS,
            HttpStatus.NOT_FOUND.value());
        error.put(MESSAGE, ex.getMessage());
        error.put(TIMESTAMP,
            LocalDateTime.now().toString());
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
            handleGenericException(
                Exception ex) {
        Map<String, Object> error =
            new HashMap<>();
        error.put(STATUS,
            HttpStatus.INTERNAL_SERVER_ERROR
                .value());
        error.put(MESSAGE,
            "Something went wrong!");
        error.put(TIMESTAMP,
            LocalDateTime.now().toString());
        return ResponseEntity
            .status(HttpStatus
                .INTERNAL_SERVER_ERROR)
            .body(error);
    }
}