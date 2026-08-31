package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    // Counts all 7 items in the raw_materials table
    @Query("SELECT COUNT(r) FROM RawMaterial r")
    long countTotalItems();

    // Counts items where current quantity is below the warning limit
    @Query("SELECT COUNT(r) FROM RawMaterial r WHERE r.currentQuantity <= r.lowStockLimit")
    long countLowStockItems();
}








//package com.cocobar.cocoapi.repository;
//
//import com.cocobar.cocoapi.model.RawMaterial;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
//}