package com.cocobar.cocoapi.dto;

public class TopSellingDTO {
    private String productName;
    private String imageUrl;
    private Long totalQuantity;
    private Double totalRevenue;
    private Long totalOrders;

    public TopSellingDTO(String productName, String imageUrl, Long totalQuantity, Double totalRevenue, Long totalOrders) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.totalQuantity = totalQuantity != null ? totalQuantity : 0L;
        this.totalRevenue = totalRevenue != null ? totalRevenue : 0.0;
        this.totalOrders = totalOrders != null ? totalOrders : 0L;
    }
    // Generate Getters
    public String getProductName() { return productName; }
    public String getImageUrl() { return imageUrl; }
    public Long getTotalQuantity() { return totalQuantity; }
    public Double getTotalRevenue() { return totalRevenue; }
    public Long getTotalOrders() { return totalOrders; }
}