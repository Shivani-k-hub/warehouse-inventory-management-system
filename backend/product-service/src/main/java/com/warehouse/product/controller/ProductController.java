package com.warehouse.product.controller;

import com.warehouse.product.dto.ProductDTO;
import com.warehouse.product.entity.Product;
import com.warehouse.product.service
    .ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation
    .BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
//@CrossOrigin(origins = "*")
@Tag(name = "Product Controller",
     description = "Product CRUD APIs")
public class ProductController {

    // ✅ Constructor injection
    private final ProductService productService;

    public ProductController(
            ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create product")
    @PostMapping
    public ResponseEntity<String>
            createProduct(
                @Valid @RequestBody
                ProductDTO productDTO,
                BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors =
                new StringBuilder();
            result.getAllErrors()
                .forEach(error -> errors
                    .append(error
                        .getDefaultMessage())
                    .append(", "));
            return ResponseEntity.badRequest()
                .body(errors.toString());
        }
        Product product = toEntity(productDTO);
        productService.createProduct(product);
        return ResponseEntity.ok(
            "Product created successfully!");
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<Product>>
            getAllProducts() {
        return ResponseEntity.ok(
            productService.getAllProducts());
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product>
            getProductById(
                @PathVariable String id) {
        return ResponseEntity.ok(
            productService
                .getProductById(id));
    }

    @Operation(summary = "Update product")
    @PutMapping("/{id}")
    public ResponseEntity<String>
            updateProduct(
                @PathVariable String id,
                @Valid @RequestBody
                ProductDTO productDTO,
                BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors =
                new StringBuilder();
            result.getAllErrors()
                .forEach(error -> errors
                    .append(error
                        .getDefaultMessage())
                    .append(", "));
            return ResponseEntity.badRequest()
                .body(errors.toString());
        }
        Product product = toEntity(productDTO);
        productService.updateProduct(
            id, product);
        return ResponseEntity.ok(
            "Product updated successfully!");
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
            deleteProduct(
                @PathVariable String id) {
        return ResponseEntity.ok(
            productService.deleteProduct(id));
    }

    @Operation(summary = "Dispatch product")
    @PutMapping("/{id}/dispatch")
    public ResponseEntity<Product>
            dispatchProduct(
                @PathVariable String id) {
        return ResponseEntity.ok(
            productService
                .dispatchProduct(id));
    }

    @Operation(summary = "Reserve product")
    @PutMapping("/{id}/reserve")
    public ResponseEntity<Product>
            reserveProduct(
                @PathVariable String id) {
        return ResponseEntity.ok(
            productService
                .reserveProduct(id));
    }

    @Operation(summary = "Restore product")
    @PutMapping("/{id}/restore")
    public ResponseEntity<Product>
            restoreProduct(
                @PathVariable String id) {
        return ResponseEntity.ok(
            productService
                .restoreProduct(id));
    }

    @Operation(summary = "Reduce quantity")
    @PutMapping("/{id}/reduce")
    public ResponseEntity<Product>
            reduceQuantity(
                @PathVariable String id,
                @RequestParam int quantity) {
        return ResponseEntity.ok(
            productService
                .reduceQuantity(id, quantity));
    }

    @Operation(summary = "Get by warehouse")
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Product>>
            getByWarehouse(
                @PathVariable
                String warehouseId) {
        return ResponseEntity.ok(
            productService
                .getProductsByWarehouse(
                    warehouseId));
    }

    @Operation(summary = "Get by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Product>>
            getByStatus(
                @PathVariable String status) {
        return ResponseEntity.ok(
            productService
                .getProductsByStatus(status));
    }
    @Operation(summary = "Get warehouse stock total")
    @GetMapping("/warehouse/{warehouseId}/stock")
    public ResponseEntity<Integer>
            getWarehouseStock(
                @PathVariable String warehouseId) {
        return ResponseEntity.ok(
            productService
                .getTotalQuantityByWarehouse(
                    warehouseId));
    }
    private Product toEntity(
            ProductDTO dto) {
        Product p = new Product();
        p.setProductName(dto.getProductName());
        p.setProductType(dto.getProductType());
        p.setProductWeight(
            dto.getProductWeight());
        p.setProductLocation(
            dto.getProductLocation());
        p.setProductQuantity(
            dto.getProductQuantity());
        p.setWarehouseId(dto.getWarehouseId());
        p.setProductStatus(
            dto.getProductStatus());
        p.setProductManufacture(
            dto.getProductManufacture());
        p.setProductExpiry(
            dto.getProductExpiry());
        p.setNotifyDaysBefore(
            dto.getNotifyDaysBefore());
        return p;
    }
}