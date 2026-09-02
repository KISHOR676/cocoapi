package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.dto.*;
import com.cocobar.cocoapi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // --- SALES HISTORY PAGE QUERIES ---
    @Query("SELECT new com.cocobar.cocoapi.dto.SalesOverviewDTO(SUM(s.totalPrice), COUNT(s), SUM(s.quantity)) " +
            "FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    SalesOverviewDTO getSalesOverview(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new com.cocobar.cocoapi.dto.TopSellingDTO(r.name, r.imageUrl, SUM(s.quantity), SUM(s.totalPrice), COUNT(s)) " +
            "FROM Sale s JOIN s.recipe r WHERE s.saleDate BETWEEN :startDate AND :endDate " +
            "GROUP BY r.id ORDER BY SUM(s.quantity) DESC")
    List<TopSellingDTO> getTopSellingProducts(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new com.cocobar.cocoapi.dto.DailySaleDTO(CAST(s.saleDate AS date), SUM(s.totalPrice), COUNT(s), SUM(s.quantity)) " +
            "FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate " +
            "GROUP BY CAST(s.saleDate AS date) ORDER BY CAST(s.saleDate AS date) DESC")
    List<DailySaleDTO> getDailySales(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Sale> findBySaleDateBetweenOrderBySaleDateDesc(LocalDateTime startDate, LocalDateTime endDate);

    // --- DASHBOARD QUERIES (STRICTLY USING 'sales' TABLE) ---

    // Get total sales specifically for today
    @Query(value = "SELECT SUM(total_price) FROM sales WHERE DATE(sale_date) = CURDATE()", nativeQuery = true)
    Double getTodaysTotalSales();

    // Get daily totals for the graph
    @Query(value = "SELECT DATE(sale_date) as saleDate, SUM(total_price) as dailyTotal " +
            "FROM sales WHERE sale_date >= :startDate " +
            "GROUP BY DATE(sale_date) ORDER BY DATE(sale_date) ASC", nativeQuery = true)
    List<Object[]> getWeeklySalesGraphData(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT new com.cocobar.cocoapi.dto.SaleDetailDTO(s.id, r.name, r.imageUrl, s.quantity, s.totalPrice, s.saleDate) " +
            "FROM Sale s JOIN s.recipe r WHERE DATE(s.saleDate) = :exactDate ORDER BY s.saleDate DESC")
    List<SaleDetailDTO> getDetailedSalesForDate(@Param("exactDate") java.time.LocalDate exactDate);

    @Query("SELECT new com.cocobar.cocoapi.dto.TopItemDTO(r.name, SUM(s.quantity)) " +
            "FROM Sale s JOIN s.recipe r WHERE DATE(s.saleDate) BETWEEN :startDate AND :endDate " +
            "GROUP BY r.name ORDER BY SUM(s.quantity) DESC")
    List<TopItemDTO> getTopItemsForRange(
            @org.springframework.data.repository.query.Param("startDate") java.time.LocalDate startDate,
            @org.springframework.data.repository.query.Param("endDate") java.time.LocalDate endDate);
}