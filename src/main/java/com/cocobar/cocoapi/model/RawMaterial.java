package com.cocobar.cocoapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;

@Entity
@Table(name = "raw_materials")
public class RawMaterial {

    // Add this inside both RawMaterial.java and Recipe.java
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;

    private String imageUrl;
    private String itemName;
    private String unitType;
    private LocalDate dateAdded;
    private Double currentQuantity;
    private Double lowStockLimit;
    private BigDecimal unitPrice;

    // Default constructor required by JPA
    public RawMaterial() {
    }

    // Getters and Setters

    // Generate the Getter and Setter for this new field
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Double getLowStockLimit() {
        return lowStockLimit;
    }

    public void setLowStockLimit(Double lowStockLimit) {
        this.lowStockLimit = lowStockLimit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}