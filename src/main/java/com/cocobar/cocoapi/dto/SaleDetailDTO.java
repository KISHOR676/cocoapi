package com.cocobar.cocoapi.dto;

import java.time.LocalDateTime;

public class SaleDetailDTO {
    private Long saleId;
    private String productName;
    private String imageUrl;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime saleDate;

    public SaleDetailDTO(Long saleId, String productName, String imageUrl, Integer quantity, Double totalPrice, LocalDateTime saleDate) {
        this.saleId = saleId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    // --- GETTERS AND SETTERS REQUIRED FOR JSON ---

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
}