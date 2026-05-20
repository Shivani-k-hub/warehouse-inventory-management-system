package com.warehouse.warehouse.dto;

public class WarehouseDTO {

    private String warehouseId; // ✅ ADD
    private String warehouseName;
    private String warehouseAddress;
    private int warehouseCapacity;

    public String getWarehouseId() {
        return warehouseId; }
    public void setWarehouseId(
            String warehouseId) {
        this.warehouseId = warehouseId; }

    public String getWarehouseName() {
        return warehouseName; }
    public void setWarehouseName(
            String warehouseName) {
        this.warehouseName = warehouseName; }

    public String getWarehouseAddress() {
        return warehouseAddress; }
    public void setWarehouseAddress(
            String warehouseAddress) {
        this.warehouseAddress =
            warehouseAddress; }

    public int getWarehouseCapacity() {
        return warehouseCapacity; }
    public void setWarehouseCapacity(
            int warehouseCapacity) {
        this.warehouseCapacity =
            warehouseCapacity; }

}