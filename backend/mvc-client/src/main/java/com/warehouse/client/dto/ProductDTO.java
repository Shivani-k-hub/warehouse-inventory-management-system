package com.warehouse.client.dto;

public class ProductDTO {
    private String productId;
    private String productName;
    private String productType;
    private double productWeight;
    private String productLocation;
    private int productQuantity;
    private String warehouseId;
    private String productStatus;

    public ProductDTO() {}

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }
    public double getProductWeight() { return productWeight; }
    public void setProductWeight(double productWeight) { this.productWeight = productWeight; }
    public String getProductLocation() { return productLocation; }
    public void setProductLocation(String productLocation) { this.productLocation = productLocation; }
    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }
    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
    public String getProductStatus() { return productStatus; }
    public void setProductStatus(String productStatus) { this.productStatus = productStatus; }
}