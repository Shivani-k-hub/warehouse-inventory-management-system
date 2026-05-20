package com.warehouse.order.controller;

import com.warehouse.order.dto.OrderDTO;
import com.warehouse.order.entity.Order;
import com.warehouse.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
//@CrossOrigin(origins = "*")
@Tag(name = "Order Controller",
     description = "Order Management APIs")
public class OrderController {

    // ✅ Constructor injection
    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create order")
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderDTO dto) {
        Order order = toEntity(dto);
        return ResponseEntity.ok(
            orderService.createOrder(order));
    }

    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<List<Order>>
            getAllOrders() {
        return ResponseEntity.ok(
            orderService.getAllOrders());
    }

    @Operation(summary = "Get order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable String id) {
        return ResponseEntity.ok(
            orderService.getOrderById(id));
    }

    @Operation(summary = "Dispatch order")
    @PutMapping("/{id}/dispatch")
    public ResponseEntity<Order> dispatchOrder(
            @PathVariable String id) {
        return ResponseEntity.ok(
            orderService.dispatchOrder(id));
    }

    @Operation(summary = "Cancel order")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable String id) {
        return ResponseEntity.ok(
            orderService.cancelOrder(id));
    }

    @Operation(summary = "Update order status")
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable String id,
            @RequestParam String status) {
        return ResponseEntity.ok(
            orderService.updateOrderStatus(
                id, status));
    }

    @Operation(summary = "Get orders by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>>
            getByStatus(
                @PathVariable String status) {
        return ResponseEntity.ok(
            orderService.getOrdersByStatus(
                status));
    }

    @Operation(summary = "Get orders by warehouse")
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Order>>
            getByWarehouse(
                @PathVariable
                String warehouseId) {
        return ResponseEntity.ok(
            orderService.getOrdersByWarehouse(
                warehouseId));
    }

    @Operation(summary = "Delete order")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable String id) {
        return ResponseEntity.ok(
            orderService.deleteOrder(id));
    }

   

    private Order toEntity(OrderDTO dto) {
        Order o = new Order();
        o.setProductId(dto.getProductId());
        o.setProductName(dto.getProductName());
        o.setWarehouseId(dto.getWarehouseId());
        o.setEmployeeId(dto.getEmployeeId());
        o.setQuantity(dto.getQuantity());
        o.setNotes(dto.getNotes());
        return o;
    }
}