package com.warehouse.order;

import com.warehouse.order.entity.Order;
import com.warehouse.order.feign.ProductClient;
import com.warehouse.order.repository
    .OrderRepository;
import com.warehouse.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension
    .ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter
    .MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api
    .Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers
    .anyInt;
import static org.mockito.ArgumentMatchers
    .anyString;
import static org.mockito.Mockito.*;
import com.warehouse.order.exception
.OrderNotFoundException;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setOrderId("ord-123");
        testOrder.setProductId("prod-123");
        testOrder.setProductName("Rice Bag");
        testOrder.setWarehouseId("wh-001");
        testOrder.setQuantity(10);
        testOrder.setOrderStatus("PENDING");
        testOrder.setOrderDate(new Date());
    }

    @Test
    void testCreateOrder() {
        when(repository.save(any(Order.class)))
            .thenReturn(testOrder);

        Order result =
            orderService.createOrder(testOrder);

        assertNotNull(result);
        assertEquals("PENDING",
            result.getOrderStatus());
        verify(repository, times(1))
            .save(any(Order.class));
    }

    @Test
    void testGetAllOrders() {
        when(repository.findAll())
            .thenReturn(
                Arrays.asList(testOrder));

        List<Order> result =
            orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetOrderById() {
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));

        Order result =
            orderService.getOrderById("ord-123");

        assertNotNull(result);
        assertEquals("ord-123",
            result.getOrderId());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(repository.findById("wrong-id"))
            .thenReturn(Optional.empty());

        assertThrows(
            OrderNotFoundException.class,
            () -> orderService
                .getOrderById("wrong-id"));
    }

    @Test
    void testDispatchOrder() {
        Map<String, Object> product =
            new HashMap<>();
        product.put("productQuantity", 50);

        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));
        when(productClient.reduceQuantity(
            anyString(), anyInt()))
            .thenReturn(product);
        when(repository.save(any(Order.class)))
            .thenReturn(testOrder);

        Order result =
            orderService.dispatchOrder("ord-123");

        assertNotNull(result);
        verify(productClient, times(1))
            .reduceQuantity(anyString(), anyInt());
    }

    @Test
    void testDispatchAlreadyCancelled() {
        testOrder.setOrderStatus("CANCELLED");
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));

        assertThrows(
            OrderNotFoundException.class,
            () -> orderService
                .dispatchOrder("ord-123"));
    }


    @Test
    void testDispatchAlreadyDispatched() {
        testOrder.setOrderStatus("DISPATCHED");
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));

        assertThrows(
            OrderNotFoundException.class,
            () -> orderService
                .dispatchOrder("ord-123"));
    }

    @Test
    void testCancelOrder() {
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));
        when(repository.save(any(Order.class)))
            .thenReturn(testOrder);

        Order result =
            orderService.cancelOrder("ord-123");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Order.class));
    }

    @Test
    void testCancelDispatchedOrder() {
        testOrder.setOrderStatus("DISPATCHED");
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));

        assertThrows(
            OrderNotFoundException.class,
            () -> orderService
                .cancelOrder("ord-123"));
    }
    
    @Test
    void testGetOrdersByStatus() {
        when(repository.findByOrderStatus(
            "PENDING"))
            .thenReturn(
                Arrays.asList(testOrder));

        List<Order> result =
            orderService
                .getOrdersByStatus("PENDING");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetOrdersByWarehouse() {
        when(repository.findByWarehouseId(
            "wh-001"))
            .thenReturn(
                Arrays.asList(testOrder));

        List<Order> result =
            orderService
                .getOrdersByWarehouse("wh-001");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(repository)
            .deleteById("ord-123");

        String result =
            orderService.deleteOrder("ord-123");

        assertEquals(
            "Order deleted successfully!",
            result);
        verify(repository, times(1))
            .deleteById("ord-123");
    }

    @Test
    void testGetOrderStatusReport() {
        Order pending = new Order();
        pending.setOrderStatus("PENDING");

        Order dispatched = new Order();
        dispatched.setOrderStatus("DISPATCHED");

        Order cancelled = new Order();
        cancelled.setOrderStatus("CANCELLED");

        when(repository.findAll())
            .thenReturn(Arrays.asList(
                pending, dispatched, cancelled));

        Map<String, Long> report =
            orderService.getOrderStatusReport();

        assertEquals(1L, report.get("PENDING"));
        assertEquals(1L,
            report.get("DISPATCHED"));
        assertEquals(1L,
            report.get("CANCELLED"));
    }

    @Test
    void testUpdateOrderStatus() {
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));
        when(repository.save(any(Order.class)))
            .thenReturn(testOrder);

        Order result =
            orderService.updateOrderStatus(
                "ord-123", "PROCESSING");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Order.class));
    }

    @Test
    void testUpdateOrderStatusToDispatched() {
        when(repository.findById("ord-123"))
            .thenReturn(Optional.of(testOrder));
        when(repository.save(any(Order.class)))
            .thenReturn(testOrder);

        Order result =
            orderService.updateOrderStatus(
                "ord-123", "DISPATCHED");

        assertNotNull(result);
        verify(repository, times(1))
            .save(any(Order.class));
    }
}