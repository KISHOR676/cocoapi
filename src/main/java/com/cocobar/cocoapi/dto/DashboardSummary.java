package com.cocobar.cocoapi.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummary {
    private long totalInventoryItems;
    private Double todaysSales;
    private Double todaysProfit;
    private long lowStockItems;
    private int totalProducts;
    private List<Map<String, Object>> salesGraphData;

    public long getTotalInventoryItems() { return totalInventoryItems; }
    public void setTotalInventoryItems(long totalInventoryItems) { this.totalInventoryItems = totalInventoryItems; }

    public Double getTodaysSales() { return todaysSales; }
    public void setTodaysSales(Double todaysSales) { this.todaysSales = todaysSales; }

    public Double getTodaysProfit() { return todaysProfit; }
    public void setTodaysProfit(Double todaysProfit) { this.todaysProfit = todaysProfit; }

    public long getLowStockItems() { return lowStockItems; }
    public void setLowStockItems(long lowStockItems) { this.lowStockItems = lowStockItems; }

    public List<Map<String, Object>> getSalesGraphData() { return salesGraphData; }
    public void setSalesGraphData(List<Map<String, Object>> salesGraphData) { this.salesGraphData = salesGraphData; }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }
}