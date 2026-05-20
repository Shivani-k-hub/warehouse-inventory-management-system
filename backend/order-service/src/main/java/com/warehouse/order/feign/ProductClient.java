package com.warehouse.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    Map<String, Object> getProductById(
        @PathVariable("id") String id);

    @PutMapping("/products/{id}/reduce")
    Map<String, Object> reduceQuantity(
        @PathVariable("id") String id,
        @RequestParam("quantity") int quantity);
}