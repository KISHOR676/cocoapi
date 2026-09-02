package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.dto.ExportReportDTO;
import com.cocobar.cocoapi.dto.TopItemDTO;
import com.cocobar.cocoapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private SaleRepository saleRepo;

    @GetMapping("/export")
    public ResponseEntity<ExportReportDTO> getExportReport(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // FIX 1: Convert LocalDate to LocalDateTime for the overview query
        // Start at 00:00:00 of the first day, end at 23:59:59 of the last day
        java.time.LocalDateTime startDateTime = startDate.atStartOfDay();
        java.time.LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        com.cocobar.cocoapi.dto.SalesOverviewDTO overview = saleRepo.getSalesOverview(startDateTime, endDateTime);
        List<TopItemDTO> topItems = saleRepo.getTopItemsForRange(startDate, endDate);

        // Keep only top 3 items
        if (topItems.size() > 3) {
            topItems = topItems.subList(0, 3);
        }

        ExportReportDTO report = new ExportReportDTO();
        report.setDate(startDateStr.equals(endDateStr) ? startDateStr : startDateStr + " to " + endDateStr);

        if (overview != null) {
            report.setTotalSales(overview.getTotalSales());

            // FIX 2: Convert the Long from the database into an Integer using .intValue()
            report.setTotalOrders(overview.getTotalOrders() != null ? overview.getTotalOrders().intValue() : 0);

            report.setAverageOrderValue(overview.getAvgOrderValue());
        } else {
            report.setTotalSales(0.0);
            report.setTotalOrders(0);
            report.setAverageOrderValue(0.0);
        }

        report.setTopItems(topItems);
        return ResponseEntity.ok(report);
    }
}