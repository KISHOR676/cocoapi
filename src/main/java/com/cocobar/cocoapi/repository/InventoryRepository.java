package com.cocobar.cocoapi.repository;
import com.cocobar.cocoapi.model.Inventory; // Assuming you have this model
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT COUNT(i) FROM Inventory i WHERE i.status = 'ACTIVE'")
    long countActiveItems();

    @Query("SELECT COUNT(i) FROM Inventory i WHERE i.currentStock <= i.minimumStockLevel")
    long countLowStockItems();
}