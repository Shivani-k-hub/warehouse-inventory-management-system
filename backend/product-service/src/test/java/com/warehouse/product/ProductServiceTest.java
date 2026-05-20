package com.warehouse.product;

import com.warehouse.product.entity.Product;
import com.warehouse.product.repository
    .ProductRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.warehouse.product.exception
.ProductNotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProductId("prod-123");
        testProduct.setProductName("Rice Bag");
        testProduct.setProductType("Food");
        testProduct.setProductWeight(25.0);
        testProduct.setProductQuantity(100);
        testProduct.setProductLocation("Rack A1");
        testProduct.setWarehouseId("wh-001");
        testProduct.setProductStatus("AVAILABLE");
    }

    @Test
    void testCreateProduct() {
        when(repository.save(any(Product.class)))
            .thenReturn(testProduct);

        Product result =
            productService
                .createProduct(testProduct);

        assertNotNull(result);
        assertEquals("Rice Bag",
            result.getProductName());
        verify(repository, times(1))
            .save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        when(repository.findAll())
            .thenReturn(
                Arrays.asList(testProduct));

        List<Product> result =
            productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductById() {
        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));

        Product result =
            productService
                .getProductById("prod-123");

        assertNotNull(result);
        assertEquals("prod-123",
            result.getProductId());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(repository.findById("wrong-id"))
            .thenReturn(Optional.empty());

        // ✅ Use ProductNotFoundException
        assertThrows(
            ProductNotFoundException.class,
            () -> productService
                .getProductById("wrong-id"));
    }

    @Test
    void testUpdateProduct() {
        when(repository.save(any(Product.class)))
            .thenReturn(testProduct);

        Product result =
            productService.updateProduct(
                "prod-123", testProduct);

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(repository)
            .deleteById("prod-123");

        String result =
            productService
                .deleteProduct("prod-123");

        assertEquals(
            "Product deleted successfully!",
            result);
        verify(repository, times(1))
            .deleteById("prod-123");
    }

    @Test
    void testGetProductsByWarehouse() {
        when(repository
            .findByWarehouseId("wh-001"))
            .thenReturn(
                Arrays.asList(testProduct));

        List<Product> result =
            productService
                .getProductsByWarehouse("wh-001");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductsByStatus() {
        when(repository
            .findByProductStatus("AVAILABLE"))
            .thenReturn(
                Arrays.asList(testProduct));

        List<Product> result =
            productService
                .getProductsByStatus("AVAILABLE");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    
    @Test
    void testDispatchProduct() {
        // product is AVAILABLE - can dispatch
        testProduct.setProductStatus("AVAILABLE");

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));
        when(repository.save(any(Product.class)))
            .thenReturn(testProduct);

        Product result =
            productService
                .dispatchProduct("prod-123");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Product.class));
    }

    @Test
    void testReserveProduct() {
        // product is AVAILABLE - can reserve
        testProduct.setProductStatus("AVAILABLE");

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));
        when(repository.save(any(Product.class)))
            .thenReturn(testProduct);

        Product result =
            productService
                .reserveProduct("prod-123");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Product.class));
    }

    @Test
    void testRestoreProduct() {
        // product is DISPATCHED - can restore
        testProduct.setProductStatus("DISPATCHED");

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));

        // After restore - status is AVAILABLE
        Product restoredProduct = new Product();
        restoredProduct.setProductId("prod-123");
        restoredProduct
            .setProductStatus("AVAILABLE");

        when(repository.save(any(Product.class)))
            .thenReturn(restoredProduct);

        Product result =
            productService
                .restoreProduct("prod-123");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Product.class));
    }
    
    @Test
    void testDispatchProductNotAvailable() {
        testProduct.setProductStatus("RESERVED");

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));

        assertThrows(
            ProductNotFoundException.class,
            () -> productService
                .dispatchProduct("prod-123"));
    }

    

    @Test
    void testReserveProductNotAvailable() {
        testProduct.setProductStatus("DISPATCHED");

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));

        assertThrows(
            ProductNotFoundException.class,
            () -> productService
                .reserveProduct("prod-123"));
    }
    

    @Test
    void testReduceQuantity() {
        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));
        when(repository.save(any(Product.class)))
            .thenReturn(testProduct);

        Product result =
            productService.reduceQuantity(
                "prod-123", 10);

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Product.class));
    }

    @Test
    void testReduceQuantityInsufficient() {
        testProduct.setProductQuantity(5);

        when(repository.findById("prod-123"))
            .thenReturn(
                Optional.of(testProduct));

        assertThrows(
            ProductNotFoundException.class,
            () -> productService
                .reduceQuantity("prod-123", 10));
    }
}