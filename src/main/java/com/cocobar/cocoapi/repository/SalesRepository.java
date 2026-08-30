package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.SalesRecord; // You will need a simple SalesRecord model too
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface SalesRepository extends JpaRepository<SalesRecord, Long> {

    // Using native MySQL queries to easily match today's date
    @Query(value = "SELECT SUM(order_total) FROM sales_records WHERE DATE(sale_date) = CURDATE()", nativeQuery = true)
    BigDecimal getTodaysTotalSales();

    @Query(value = "SELECT SUM(profit_margin) FROM sales_records WHERE DATE(sale_date) = CURDATE()", nativeQuery = true)
    BigDecimal getTodaysTotalProfit();

    // Add this below your existing queries
//    @Query(value = "SELECT DATE(sale_date) as saleDate, SUM(order_total) as dailyTotal FROM sales_records GROUP BY DATE(sale_date) ORDER BY DATE(sale_date) ASC LIMIT 7", nativeQuery = true)
//    List<Object[]> getWeeklySalesData();

    @Query(value = "SELECT DATE(sale_date) as saleDate, SUM(order_total) as dailyTotal FROM sales_records WHERE sale_date >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) GROUP BY DATE(sale_date) ORDER BY DATE(sale_date) ASC", nativeQuery = true)
    List<Object[]> getWeeklySalesData();
}