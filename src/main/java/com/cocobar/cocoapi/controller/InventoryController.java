package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.model.RawMaterial;
import com.cocobar.cocoapi.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private RawMaterialRepository repository;

    @GetMapping
    public List<RawMaterial> getAllInventory() {
        return repository.findAll();
    }

    @PostMapping
    public RawMaterial addRawMaterial(@RequestBody RawMaterial item) {
        if (item.getDateAdded() == null) {
            item.setDateAdded(LocalDate.now());
        }
        return repository.save(item);
    }

    // NEW: Update Quantity and Price endpoint
    @PutMapping("/{id}")
    public RawMaterial updateItem(@PathVariable Long id, @RequestBody RawMaterial updatedData) {
        RawMaterial existingItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Update the changing values
        existingItem.setCurrentQuantity(updatedData.getCurrentQuantity());
        existingItem.setUnitPrice(updatedData.getUnitPrice());

        return repository.save(existingItem);
    }

    // NEW: Delete Item endpoint
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}