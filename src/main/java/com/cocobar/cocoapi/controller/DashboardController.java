package com.cocobar.cocoapi.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cocobar.cocoapi.dto.DashboardSummary;
import com.cocobar.cocoapi.repository.RawMaterialRepository;
import com.cocobar.cocoapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private RawMaterialRepository rawMaterialRepo;

    @Autowired
    private SaleRepository saleRepo;

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummary> getDashboardData() {
        DashboardSummary summary = new DashboardSummary();

        // 1. Total Inventory (From raw_materials)
        summary.setTotalInventoryItems(rawMaterialRepo.countTotalItems());
        summary.setLowStockItems(rawMaterialRepo.countLowStockItems());

        // 2. Today's Real Sales (From sales table)
        Double todaysSales = saleRepo.getTodaysTotalSales();
        if (todaysSales == null) {
            todaysSales = 0.0;
        }
        summary.setTodaysSales(todaysSales);

        // 3. Today's Profit (Calculated as 40% of real sales)
        // You can adjust the 0.40 to whatever your actual average profit margin is
        summary.setTodaysProfit(todaysSales * 0.40);

        // 4. Graph Data (From sales table)
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(6).with(LocalTime.MIN);
        List<Object[]> weeklySales = saleRepo.getWeeklySalesGraphData(sevenDaysAgo);

        List<Map<String, Object>> graphData = new ArrayList<>();
        for(Object[] row : weeklySales) {
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("date", row[0].toString());
            dataPoint.put("total", row[1]);
            graphData.add(dataPoint);
        }
        summary.setSalesGraphData(graphData);

        return ResponseEntity.ok(summary);
    }
}








//package com.cocobar.cocoapi.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import com.cocobar.cocoapi.dto.DashboardSummary;
//import com.cocobar.cocoapi.repository.InventoryRepository;
//import com.cocobar.cocoapi.repository.SalesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.math.BigDecimal;
//
//
//@RestController
//@RequestMapping("/api/dashboard")
//public class DashboardController {
//
//    @Autowired
//    private InventoryRepository inventoryRepo;
//
//    @Autowired
//    private SalesRepository salesRepo;
//
//    @GetMapping("/summary")
//    public ResponseEntity<DashboardSummary> getDashboardData() {
//        DashboardSummary summary = new DashboardSummary();
//
//        // Fetch real data. Handle nulls in case there are zero sales today.
//        BigDecimal todaysSales = salesRepo.getTodaysTotalSales();
//        BigDecimal todaysProfit = salesRepo.getTodaysTotalProfit();
//
//        summary.setTotalInventoryItems(inventoryRepo.countActiveItems());
//        summary.setLowStockItems(inventoryRepo.countLowStockItems());
//        summary.setTodaysSales(todaysSales != null ? todaysSales : BigDecimal.ZERO);
//        summary.setTodaysProfit(todaysProfit != null ? todaysProfit : BigDecimal.ZERO);
//
//        // --- NEW CHART DATA LOGIC ---
//        List<Object[]> weeklySales = salesRepo.getWeeklySalesData();
//        List<Map<String, Object>> graphData = new ArrayList<>();
//
//        for(Object[] row : weeklySales) {
//            Map<String, Object> dataPoint = new HashMap<>();
//            dataPoint.put("date", row[0].toString());
//            dataPoint.put("total", row[1]); // The sum of sales for that day
//            graphData.add(dataPoint);
//        }
//        summary.setSalesGraphData(graphData);
//        // -----------------------------
//
//        return ResponseEntity.ok(summary);
//    }
//}