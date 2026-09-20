package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    // FIXED: Now only counts ACTIVE items
    @Query("SELECT COUNT(r) FROM RawMaterial r WHERE r.isActive = true")
    long countTotalItems();

    // FIXED: Now only counts ACTIVE items that are below the warning limit
    @Query("SELECT COUNT(r) FROM RawMaterial r WHERE r.isActive = true AND r.currentQuantity <= r.lowStockLimit")
    long countLowStockItems();

    List<RawMaterial> findByIsActiveTrue();
}



//package com.cocobar.cocoapi.repository;
//
//import com.cocobar.cocoapi.model.RawMaterial;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import java.util.List;
//
//public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
//
//    // Counts all 7 items in the raw_materials table
//    @Query("SELECT COUNT(r) FROM RawMaterial r")
//    long countTotalItems();
//
//    // Counts items where current quantity is below the warning limit
//    @Query("SELECT COUNT(r) FROM RawMaterial r WHERE r.currentQuantity <= r.lowStockLimit")
//    long countLowStockItems();
//
//    List<RawMaterial> findByIsActiveTrue();
//}