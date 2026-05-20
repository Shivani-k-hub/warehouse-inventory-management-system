package com.warehouse.product;

import com.warehouse.product.controller
    .ProductController;
import com.warehouse.product.dto.ProductDTO;
import com.warehouse.product.entity.Product;
import com.warehouse.product.service
    .ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation
    .BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers
    .anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;
    private ProductDTO testDTO;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProductId("prod-123");
        testProduct.setProductName("Rice Bag");
        testProduct.setProductType("Food");
        testProduct.setProductWeight(25.0);
        testProduct.setProductQuantity(100);
        testProduct.setProductLocation(
            "Rack A1");
        testProduct.setWarehouseId("wh-001");
        testProduct.setProductStatus(
            "AVAILABLE");

        testDTO = new ProductDTO();
        testDTO.setProductName("Rice Bag");
        testDTO.setProductType("Food");
        testDTO.setProductWeight(25.0);
        testDTO.setProductQuantity(100);
        testDTO.setProductLocation("Rack A1");
        testDTO.setWarehouseId("wh-001");
        testDTO.setProductStatus("AVAILABLE");
    }

    @Test
    void testCreateProductSuccess() {
        when(bindingResult.hasErrors())
            .thenReturn(false);
        when(productService.createProduct(
            any(Product.class)))
            .thenReturn(testProduct);

        ResponseEntity<String> response =
            productController.createProduct(
                testDTO, bindingResult);

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testCreateProductValidationError() {
        when(bindingResult.hasErrors())
            .thenReturn(true);
        when(bindingResult.getAllErrors())
            .thenReturn(Collections.emptyList());

        ResponseEntity<String> response =
            productController.createProduct(
                testDTO, bindingResult);

        assertEquals(400,
            response.getStatusCode().value());
    }

    @Test
    void testGetAllProducts() {
        when(productService.getAllProducts())
            .thenReturn(
                Arrays.asList(testProduct));

        ResponseEntity<List<Product>> response =
            productController.getAllProducts();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(
            "prod-123"))
            .thenReturn(testProduct);

        ResponseEntity<Product> response =
            productController
                .getProductById("prod-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("prod-123",
            response.getBody().getProductId());
    }

    @Test
    void testUpdateProductSuccess() {
        when(bindingResult.hasErrors())
            .thenReturn(false);
        when(productService.updateProduct(
            anyString(), any(Product.class)))
            .thenReturn(testProduct);

        ResponseEntity<String> response =
            productController.updateProduct(
                "prod-123", testDTO,
                bindingResult);

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testUpdateProductValidationError() {
        when(bindingResult.hasErrors())
            .thenReturn(true);
        when(bindingResult.getAllErrors())
            .thenReturn(Collections.emptyList());

        ResponseEntity<String> response =
            productController.updateProduct(
                "prod-123", testDTO,
                bindingResult);

        assertEquals(400,
            response.getStatusCode().value());
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProduct(
            "prod-123"))
            .thenReturn(
                "Product deleted successfully!");

        ResponseEntity<String> response =
            productController
                .deleteProduct("prod-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Product deleted successfully!",
            response.getBody());
    }

    @Test
    void testDispatchProduct() {
        testProduct.setProductStatus(
            "DISPATCHED");
        when(productService.dispatchProduct(
            "prod-123"))
            .thenReturn(testProduct);

        ResponseEntity<Product> response =
            productController
                .dispatchProduct("prod-123");

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testReserveProduct() {
        testProduct.setProductStatus("RESERVED");
        when(productService.reserveProduct(
            "prod-123"))
            .thenReturn(testProduct);

        ResponseEntity<Product> response =
            productController
                .reserveProduct("prod-123");

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testRestoreProduct() {
        testProduct.setProductStatus(
            "AVAILABLE");
        when(productService.restoreProduct(
            "prod-123"))
            .thenReturn(testProduct);

        ResponseEntity<Product> response =
            productController
                .restoreProduct("prod-123");

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testGetByWarehouse() {
        when(productService
            .getProductsByWarehouse("wh-001"))
            .thenReturn(
                Arrays.asList(testProduct));

        ResponseEntity<List<Product>> response =
            productController
                .getByWarehouse("wh-001");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetByStatus() {
        when(productService
            .getProductsByStatus("AVAILABLE"))
            .thenReturn(
                Arrays.asList(testProduct));

        ResponseEntity<List<Product>> response =
            productController
                .getByStatus("AVAILABLE");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testReduceQuantity() {
        when(productService.reduceQuantity(
            "prod-123", 10))
            .thenReturn(testProduct);

        ResponseEntity<Product> response =
            productController
                .reduceQuantity("prod-123", 10);

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testGetByWarehouseNotEmpty() {
        when(productService
            .getProductsByWarehouse("wh-002"))
            .thenReturn(
                Arrays.asList(testProduct));

        ResponseEntity<List<Product>> response =
            productController
                .getByWarehouse("wh-002");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("Rice Bag",
            response.getBody()
                .get(0).getProductName());
    }
}