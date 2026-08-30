package com.cocobar.cocoapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_items")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "current_stock", nullable = false)
    private Double currentStock;

    @Column(name = "minimum_stock_level", nullable = false)
    private Double minimumStockLevel;

    @Column(name = "status")
    private String status = "ACTIVE";

    // Generate standard Getters and Setters here
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getCurrentStock() { return currentStock; }
    public void setCurrentStock(Double currentStock) { this.currentStock = currentStock; }
    public Double getMinimumStockLevel() { return minimumStockLevel; }
    public void setMinimumStockLevel(Double minimumStockLevel) { this.minimumStockLevel = minimumStockLevel; }
}