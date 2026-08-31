package com.cocobar.cocoapi.dto;
import java.time.LocalDate;

public class DailySaleDTO {
    private LocalDate date;
    private Double dailyTotal;
    private Long totalOrders;
    private Long itemsSold;

    public DailySaleDTO(java.sql.Date date, Double dailyTotal, Long totalOrders, Long itemsSold) {
        this.date = date.toLocalDate();
        this.dailyTotal = dailyTotal != null ? dailyTotal : 0.0;
        this.totalOrders = totalOrders != null ? totalOrders : 0L;
        this.itemsSold = itemsSold != null ? itemsSold : 0L;
    }
    // Generate Getters
    public LocalDate getDate() { return date; }
    public Double getDailyTotal() { return dailyTotal; }
    public Long getTotalOrders() { return totalOrders; }
    public Long getItemsSold() { return itemsSold; }
}