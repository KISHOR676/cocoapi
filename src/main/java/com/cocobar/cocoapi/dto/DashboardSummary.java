package com.cocobar.cocoapi.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummary {
    private long totalInventoryItems;
    private Double todaysSales;
    private Double todaysProfit;
    private long lowStockItems;
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
}








//package com.cocobar.cocoapi.dto;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
//public class DashboardSummary {
//    private long totalInventoryItems;
//    private BigDecimal todaysSales;
//    private BigDecimal todaysProfit;
//    private long lowStockItems;
//    private List<Map<String, Object>> salesGraphData;
//
//
//    // --- Complete Getters and Setters ---
//
//    public long getTotalInventoryItems() {
//        return totalInventoryItems;
//    }
//
////    // Add Getter and Setter for userName
////    public String getUserName() { return userName; }
////
////    public void setUserName(String userName) { this.userName = userName; }
//
//    public void setTotalInventoryItems(long totalInventoryItems) {
//        this.totalInventoryItems = totalInventoryItems;
//    }
//
//    public BigDecimal getTodaysSales() {
//        return todaysSales;
//    }
//
//    public void setTodaysSales(BigDecimal todaysSales) {
//        this.todaysSales = todaysSales;
//    }
//
//    public BigDecimal getTodaysProfit() {
//        return todaysProfit;
//    }
//
//    public void setTodaysProfit(BigDecimal todaysProfit) {
//        this.todaysProfit = todaysProfit;
//    }
//
//    public long getLowStockItems() {
//        return lowStockItems;
//    }
//
//    public void setLowStockItems(long lowStockItems) {
//        this.lowStockItems = lowStockItems;
//    }
//
//    public List<Map<String, Object>> getSalesGraphData() {
//        return salesGraphData;
//    }
//
//    public void setSalesGraphData(List<Map<String, Object>> salesGraphData) {
//        this.salesGraphData = salesGraphData;
//    }
//}