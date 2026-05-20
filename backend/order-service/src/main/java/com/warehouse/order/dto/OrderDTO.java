package com.warehouse.order.dto;

public class OrderDTO {

    private String productId;
    private String productName;
    private String warehouseId;
    private String employeeId;
    private int quantity;
    private String notes;

    public String getProductId() {
        return productId; }
    public void setProductId(
            String productId) {
        this.productId = productId; }

    public String getProductName() {
        return productName; }
    public void setProductName(
            String productName) {
        this.productName = productName; }

    public String getWarehouseId() {
        return warehouseId; }
    public void setWarehouseId(
            String warehouseId) {
        this.warehouseId = warehouseId; }

    public String getEmployeeId() {
        return employeeId; }
    public void setEmployeeId(
            String employeeId) {
        this.employeeId = employeeId; }

    public int getQuantity() {
        return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity; }

    public String getNotes() {
        return notes; }
    public void setNotes(String notes) {
        this.notes = notes; }
}