package com.cocobar.cocoapi.dto;

public class SalesOverviewDTO {
    private Double totalSales;
    private Long totalOrders;
    private Long itemsSold;
    private Double avgOrderValue;

    public SalesOverviewDTO(Double totalSales, Long totalOrders, Long itemsSold) {
        this.totalSales = totalSales != null ? totalSales : 0.0;
        this.totalOrders = totalOrders != null ? totalOrders : 0L;
        this.itemsSold = itemsSold != null ? itemsSold : 0L;
        this.avgOrderValue = this.totalOrders > 0 ? this.totalSales / this.totalOrders : 0.0;
    }
    // Generate Getters and Setters for all 4 variables
    public Double getTotalSales() { return totalSales; }
    public Long getTotalOrders() { return totalOrders; }
    public Long getItemsSold() { return itemsSold; }
    public Double getAvgOrderValue() { return avgOrderValue; }
}