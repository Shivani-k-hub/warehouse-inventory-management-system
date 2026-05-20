package com.warehouse.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public class ProductDTO {

    @NotBlank(message = "Name required!")
    private String productName;

    @NotBlank(message = "Type required!")
    private String productType;

    private double productWeight;

    @NotBlank(message = "Location required!")
    private String productLocation;

    @Min(value = 0,
         message = "Quantity cannot be negative!")
    private int productQuantity;

    private String warehouseId;
    private String productStatus;
    private Date productManufacture;
    private Date productExpiry;
    private Integer notifyDaysBefore;

    public String getProductName() {
        return productName; }
    public void setProductName(
            String productName) {
        this.productName = productName; }

    public String getProductType() {
        return productType; }
    public void setProductType(
            String productType) {
        this.productType = productType; }

    public double getProductWeight() {
        return productWeight; }
    public void setProductWeight(
            double productWeight) {
        this.productWeight = productWeight; }

    public String getProductLocation() {
        return productLocation; }
    public void setProductLocation(
            String productLocation) {
        this.productLocation =
            productLocation; }

    public int getProductQuantity() {
        return productQuantity; }
    public void setProductQuantity(
            int productQuantity) {
        this.productQuantity =
            productQuantity; }

    public String getWarehouseId() {
        return warehouseId; }
    public void setWarehouseId(
            String warehouseId) {
        this.warehouseId = warehouseId; }

    public String getProductStatus() {
        return productStatus; }
    public void setProductStatus(
            String productStatus) {
        this.productStatus = productStatus; }

    public Date getProductManufacture() {
        return productManufacture; }
    public void setProductManufacture(
            Date productManufacture) {
        this.productManufacture =
            productManufacture; }

    public Date getProductExpiry() {
        return productExpiry; }
    public void setProductExpiry(
            Date productExpiry) {
        this.productExpiry = productExpiry; }

    public Integer getNotifyDaysBefore() {
        return notifyDaysBefore; }
    public void setNotifyDaysBefore(
            Integer notifyDaysBefore) {
        this.notifyDaysBefore =
            notifyDaysBefore; }
}