package com.warehouse.product.exception;

public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(
            String message) {
        super(message);
    }
}