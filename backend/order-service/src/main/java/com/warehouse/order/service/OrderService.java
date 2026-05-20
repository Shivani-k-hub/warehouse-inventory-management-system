package com.warehouse.order.service;

import com.warehouse.order.entity.Order;
import com.warehouse.order.exception
    .OrderNotFoundException;
import com.warehouse.order.feign.ProductClient;
import com.warehouse.order.repository
    .OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger logger =
        LoggerFactory.getLogger(
            OrderService.class);

    // ✅ Constants
    private static final String DISPATCHED =
        "DISPATCHED";
    private static final String CANCELLED =
        "CANCELLED";
    private static final String PENDING =
        "PENDING";

    // ✅ Constructor injection
    private final OrderRepository repository;
    private final ProductClient productClient;

    public OrderService(
            OrderRepository repository,
            ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }

    public Order createOrder(Order order) {
        order.setOrderId(
            UUID.randomUUID().toString());
        order.setOrderDate(new Date());
        order.setOrderStatus(PENDING);
        logger.info(
            "Creating order for product: {}",
            order.getProductId());
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(String id) {
        return repository.findById(id)
            .orElseThrow(() ->
                // ✅ Custom exception
                new OrderNotFoundException(
                    "Order not found: " + id));
    }

    public Order dispatchOrder(String id) {
        Order order = getOrderById(id);

        if (CANCELLED.equals(
                order.getOrderStatus())) {
            throw new OrderNotFoundException(
                "Cannot dispatch " +
                "cancelled order!");
        }

        if (DISPATCHED.equals(
                order.getOrderStatus())) {
            throw new OrderNotFoundException(
                "Order already dispatched!");
        }

        try {
            productClient.reduceQuantity(
                order.getProductId(),
                order.getQuantity());

            logger.info(
                "Reduced product {} by {}",
                order.getProductId(),
                order.getQuantity());

        } catch (Exception e) {
            logger.error(
                "Failed to reduce qty: {}",
                e.getMessage());
            throw new OrderNotFoundException(
                "Failed to update product " +
                "quantity: " + e.getMessage());
        }

        order.setOrderStatus(DISPATCHED);
        order.setDispatchDate(new Date());
        logger.info("Order {} dispatched!", id);
        return repository.save(order);
    }

    public Order cancelOrder(String id) {
        Order order = getOrderById(id);

        if (DISPATCHED.equals(
                order.getOrderStatus())) {
            throw new OrderNotFoundException(
                "Cannot cancel " +
                "dispatched order!");
        }

        order.setOrderStatus(CANCELLED);
        logger.info("Order {} cancelled!", id);
        return repository.save(order);
    }

    public Order updateOrderStatus(
            String id, String status) {
        Order order = getOrderById(id);
        order.setOrderStatus(status);
        if (DISPATCHED.equals(status)) {
            order.setDispatchDate(new Date());
        }
        return repository.save(order);
    }

    public List<Order> getOrdersByStatus(
            String status) {
        return repository
            .findByOrderStatus(status);
    }

    public List<Order> getOrdersByWarehouse(
            String warehouseId) {
        return repository
            .findByWarehouseId(warehouseId);
    }

    public String deleteOrder(String id) {
        repository.deleteById(id);
        return "Order deleted successfully!";
    }

    public Map<String, Long>
            getOrderStatusReport() {
        List<Order> all = getAllOrders();
        Map<String, Long> report =
            new HashMap<>();
        report.put(PENDING,
            all.stream()
               .filter(o -> PENDING.equals(
                   o.getOrderStatus()))
               .count());
        report.put(DISPATCHED,
            all.stream()
               .filter(o -> DISPATCHED.equals(
                   o.getOrderStatus()))
               .count());
        report.put(CANCELLED,
            all.stream()
               .filter(o -> CANCELLED.equals(
                   o.getOrderStatus()))
               .count());
        return report;
    }
}