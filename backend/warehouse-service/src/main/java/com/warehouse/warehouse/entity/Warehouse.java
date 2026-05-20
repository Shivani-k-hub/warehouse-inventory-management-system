package com.warehouse.warehouse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "warehouse_address")
    private String warehouseAddress;

    @Column(name = "warehouse_capacity")
    private int warehouseCapacity;

    @Column(name = "warehouse_current")
    private int warehouseCurrent;

    @Column(name = "warehouse_description")
    private String warehouseDescription;

    public Warehouse() {
        // Default constructor required by JPA
    }

    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    public String getWarehouseAddress() { return warehouseAddress; }
    public void setWarehouseAddress(String warehouseAddress) { this.warehouseAddress = warehouseAddress; }
    public int getWarehouseCapacity() { return warehouseCapacity; }
    public void setWarehouseCapacity(int warehouseCapacity) { this.warehouseCapacity = warehouseCapacity; }
    public int getWarehouseCurrent() { return warehouseCurrent; }
    public void setWarehouseCurrent(int warehouseCurrent) { this.warehouseCurrent = warehouseCurrent; }
    public String getWarehouseDescription() { return warehouseDescription; }
    public void setWarehouseDescription(String warehouseDescription) { this.warehouseDescription = warehouseDescription; }
}