package com.warehouse.client.dto;

public class WarehouseDTO {
    private String warehouseId;
    private String warehouseName;
    private String warehouseAddress;
    private int warehouseCapacity;
    private int warehouseCurrent;
    private String warehouseDescription;

    public WarehouseDTO() {}

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