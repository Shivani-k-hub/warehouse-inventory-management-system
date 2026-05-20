package com.warehouse.order.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "order_status")
    private String orderStatus;
    // PENDING/PROCESSING/DISPATCHED/CANCELLED

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "dispatch_date")
    private Date dispatchDate;

    @Column(name = "notes")
    private String notes;

    public Order() {
        // Default constructor required by JPA
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public Date getDispatchDate() { return dispatchDate; }
    public void setDispatchDate(Date dispatchDate) { this.dispatchDate = dispatchDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}