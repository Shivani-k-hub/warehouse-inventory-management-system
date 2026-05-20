package com.warehouse.order;

import com.warehouse.order.controller
    .OrderController;
import com.warehouse.order.dto.OrderDTO;
import com.warehouse.order.entity.Order;
import com.warehouse.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private Order testOrder;
    private OrderDTO testDTO;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setOrderId("ord-123");
        testOrder.setProductId("prod-123");
        testOrder.setProductName("Rice Bag");
        testOrder.setQuantity(10);
        testOrder.setOrderStatus("PENDING");

        testDTO = new OrderDTO();
        testDTO.setProductId("prod-123");
        testDTO.setProductName("Rice Bag");
        testDTO.setWarehouseId("wh-001");
        testDTO.setQuantity(10);
        testDTO.setNotes("Test order");
    }

    @Test
    void testCreateOrder() {
        when(orderService.createOrder(
            any(Order.class)))
            .thenReturn(testOrder);

        ResponseEntity<Order> response =
            orderController.createOrder(testDTO);

        assertEquals(200,
            response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetAllOrders() {
        when(orderService.getAllOrders())
            .thenReturn(
                Arrays.asList(testOrder));

        ResponseEntity<List<Order>> response =
            orderController.getAllOrders();

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetOrderById() {
        when(orderService.getOrderById(
            "ord-123"))
            .thenReturn(testOrder);

        ResponseEntity<Order> response =
            orderController
                .getOrderById("ord-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("ord-123",
            response.getBody().getOrderId());
    }

    @Test
    void testDispatchOrder() {
        testOrder.setOrderStatus("DISPATCHED");
        when(orderService.dispatchOrder(
            "ord-123"))
            .thenReturn(testOrder);

        ResponseEntity<Order> response =
            orderController
                .dispatchOrder("ord-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("DISPATCHED",
            response.getBody().getOrderStatus());
    }

    @Test
    void testCancelOrder() {
        testOrder.setOrderStatus("CANCELLED");
        when(orderService.cancelOrder("ord-123"))
            .thenReturn(testOrder);

        ResponseEntity<Order> response =
            orderController
                .cancelOrder("ord-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals("CANCELLED",
            response.getBody().getOrderStatus());
    }

    @Test
    void testUpdateStatus() {
        when(orderService.updateOrderStatus(
            "ord-123", "PROCESSING"))
            .thenReturn(testOrder);

        ResponseEntity<Order> response =
            orderController.updateStatus(
                "ord-123", "PROCESSING");

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testGetByStatus() {
        when(orderService.getOrdersByStatus(
            "PENDING"))
            .thenReturn(
                Arrays.asList(testOrder));

        ResponseEntity<List<Order>> response =
            orderController.getByStatus(
                "PENDING");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(1,
            response.getBody().size());
    }

    @Test
    void testGetByWarehouse() {
        when(orderService.getOrdersByWarehouse(
            "wh-001"))
            .thenReturn(
                Arrays.asList(testOrder));

        ResponseEntity<List<Order>> response =
            orderController
                .getByWarehouse("wh-001");

        assertEquals(200,
            response.getStatusCode().value());
    }

    @Test
    void testDeleteOrder() {
        when(orderService.deleteOrder("ord-123"))
            .thenReturn(
                "Order deleted successfully!");

        ResponseEntity<String> response =
            orderController
                .deleteOrder("ord-123");

        assertEquals(200,
            response.getStatusCode().value());
        assertEquals(
            "Order deleted successfully!",
            response.getBody());
    }

   
}