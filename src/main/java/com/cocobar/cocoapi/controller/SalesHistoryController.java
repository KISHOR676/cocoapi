package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.dto.*;
import com.cocobar.cocoapi.model.Sale;
import com.cocobar.cocoapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class SalesHistoryController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/overview")
    public ResponseEntity<SalesOverviewDTO> getOverview(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(saleRepository.getSalesOverview(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)));
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<TopSellingDTO>> getTopSelling(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(saleRepository.getTopSellingProducts(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)));
    }

    @GetMapping("/daily")
    public ResponseEntity<List<DailySaleDTO>> getDailySales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(saleRepository.getDailySales(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Sale>> getRecentSales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(saleRepository.findBySaleDateBetweenOrderBySaleDateDesc(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)));
    }
}