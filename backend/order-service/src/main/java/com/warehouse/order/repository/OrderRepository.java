package com.warehouse.order.repository;

import com.warehouse.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, String> {

    List<Order> findByOrderStatus(
        String status);
    List<Order> findByProductId(
        String productId);
    List<Order> findByWarehouseId(
        String warehouseId);
    List<Order> findByEmployeeId(
        String employeeId);
}