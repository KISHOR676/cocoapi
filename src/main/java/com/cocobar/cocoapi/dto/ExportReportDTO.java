package com.cocobar.cocoapi.dto;
import java.util.List;

public class ExportReportDTO {
    private String date;
    private Double totalSales;
    private Integer totalOrders;
    private Double averageOrderValue;
    private List<TopItemDTO> topItems;

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Double getTotalSales() { return totalSales; }
    public void setTotalSales(Double totalSales) { this.totalSales = totalSales; }
    public Integer getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Integer totalOrders) { this.totalOrders = totalOrders; }
    public Double getAverageOrderValue() { return averageOrderValue; }
    public void setAverageOrderValue(Double averageOrderValue) { this.averageOrderValue = averageOrderValue; }
    public List<TopItemDTO> getTopItems() { return topItems; }
    public void setTopItems(List<TopItemDTO> topItems) { this.topItems = topItems; }
}