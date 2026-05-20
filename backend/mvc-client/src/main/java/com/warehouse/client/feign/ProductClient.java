package com.warehouse.client.feign;

import com.warehouse.client.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDTO> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable String id);

    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO product);

    @PutMapping("/products/{id}")
    ProductDTO updateProduct(@PathVariable String id,
                             @RequestBody ProductDTO product);

    @DeleteMapping("/products/{id}")
    String deleteProduct(@PathVariable String id);
}