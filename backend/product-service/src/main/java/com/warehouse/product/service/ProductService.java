package com.warehouse.product.service;

import com.warehouse.product.entity.Product;
import com.warehouse.product.exception.ProductNotFoundException;
import com.warehouse.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger logger =
        LoggerFactory.getLogger(
            ProductService.class);

    private static final String AVAILABLE =
        "AVAILABLE";
    private static final String DISPATCHED =
        "DISPATCHED";
    private static final String RESERVED =
        "RESERVED";

    private final ProductRepository repository;

    public ProductService(
            ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(
            Product product) {
        product.setProductId(
            UUID.randomUUID().toString());

        // ✅ Auto set status by quantity
        if (product.getProductQuantity() <= 0) {
            product.setProductStatus(DISPATCHED);
        } else {
            product.setProductStatus(AVAILABLE);
        }

        product.setProductLastUpdated(
            new Date());
        logger.info("Creating product: {}",
            product.getProductName());
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(
            String id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new ProductNotFoundException(
                    "Product not found: " + id));
    }

    public Product updateProduct(
            String id, Product product) {
        product.setProductId(id);

        // ✅ Auto update status
        if (product.getProductQuantity() <= 0) {
            product.setProductStatus(DISPATCHED);
        } else if (product.getProductStatus()
                   == null) {
            product.setProductStatus(AVAILABLE);
        }

        product.setProductLastUpdated(
            new Date());
        logger.info("Updating product: {}", id);
        return repository.save(product);
    }

    public String deleteProduct(String id) {
        repository.deleteById(id);
        logger.info("Deleted product: {}", id);
        return "Product deleted successfully!";
    }

    public List<Product>
            getProductsByWarehouse(
                String warehouseId) {
        return repository
            .findByWarehouseId(warehouseId);
    }

    public List<Product> getProductsByStatus(
            String status) {
        return repository
            .findByProductStatus(status);
    }

    // ✅ Get total quantity for a warehouse
    public int getTotalQuantityByWarehouse(
            String warehouseId) {
        List<Product> products =
            repository.findByWarehouseId(
                warehouseId);
        return products.stream()
            .mapToInt(
                Product::getProductQuantity)
            .sum();
    }

    public Product dispatchProduct(
            String id) {
        Product product = getProductById(id);

        if (!AVAILABLE.equals(
                product.getProductStatus())) {
            throw new ProductNotFoundException(
                "Product not available: " + id);
        }

        product.setProductStatus(DISPATCHED);
        product.setProductLastUpdated(
            new Date());
        logger.info("Product {} dispatched!",
            id);
        return repository.save(product);
    }

    public Product reserveProduct(
            String id) {
        Product product = getProductById(id);

        if (!AVAILABLE.equals(
                product.getProductStatus())) {
            throw new ProductNotFoundException(
                "Product not available: " + id);
        }

        product.setProductStatus(RESERVED);
        product.setProductLastUpdated(
            new Date());
        logger.info("Product {} reserved!", id);
        return repository.save(product);
    }

    public Product restoreProduct(
            String id) {
        Product product = getProductById(id);
        product.setProductStatus(AVAILABLE);
        product.setProductLastUpdated(
            new Date());
        logger.info("Product {} restored!", id);
        return repository.save(product);
    }

    public Product reduceQuantity(
            String id, int quantity) {
        Product product = getProductById(id);
        int current =
            product.getProductQuantity();

        if (current < quantity) {
            throw new ProductNotFoundException(
                "Insufficient quantity! " +
                "Available: " + current);
        }

        int newQty = current - quantity;
        product.setProductQuantity(newQty);

        // ✅ Auto update status
        if (newQty <= 0) {
            product.setProductStatus(
                DISPATCHED);
        }

        product.setProductLastUpdated(
            new Date());
        logger.info(
            "Product {} qty: {} -> {}",
            id, current, newQty);
        return repository.save(product);
    }
}