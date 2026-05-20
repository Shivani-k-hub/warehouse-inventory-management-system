package com.warehouse.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    private String productId;

    @NotBlank(message = "Name required!")
    @Column(name = "product_name")
    private String productName;

    @NotBlank(message = "Type required!")
    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_weight")
    private double productWeight;

    @Column(name = "product_manufacture")
    private Date productManufacture;

    @Column(name = "product_expiry")
    private Date productExpiry;

    // ← NEW FIELD!
    @Column(name = "notify_days_before")
    private Integer notifyDaysBefore;

    @NotBlank(message = "Location required!")
    @Column(name = "product_location")
    private String productLocation;

    @Min(value = 0,
         message = "Quantity cannot be negative!")
    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "product_last_updated")
    private Date productLastUpdated;

    // Getters and Setters
    public String getProductId() {
        return productId; }
    public void setProductId(String productId) {
        this.productId = productId; }

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

    public Date getProductLastUpdated() {
        return productLastUpdated; }
    public void setProductLastUpdated(
            Date productLastUpdated) {
        this.productLastUpdated =
            productLastUpdated; }
}