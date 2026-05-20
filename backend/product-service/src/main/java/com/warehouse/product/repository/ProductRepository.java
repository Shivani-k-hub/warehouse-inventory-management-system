package com.warehouse.product.repository;

import com.warehouse.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, String> {

    List<Product> findByWarehouseId(String warehouseId);
    List<Product> findByProductStatus(String status);
    List<Product> findByProductType(String type);
}