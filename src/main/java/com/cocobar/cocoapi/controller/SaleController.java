package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.model.*;
import com.cocobar.cocoapi.repository.RawMaterialRepository;
import com.cocobar.cocoapi.repository.RecipeRepository;
import com.cocobar.cocoapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> recordSale(@RequestBody SaleRequest request) {
        Recipe recipe = recipeRepository.findById(request.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 1. Deduct Inventory using FRESH database records
        for (RecipeIngredient ingredient : recipe.getIngredients()) {

            // FIX: Force fetch the most up-to-date raw material from the DB
            RawMaterial freshMaterial = rawMaterialRepository.findById(ingredient.getRawMaterial().getItemNo())
                    .orElseThrow(() -> new RuntimeException("Material not found"));

            double totalRequired = ingredient.getQuantityRequired() * request.getQuantity();

            // Check against the fresh material quantity
            if (freshMaterial.getCurrentQuantity() < totalRequired) {
                return ResponseEntity.badRequest().body("Insufficient stock for " + freshMaterial.getItemName());
            }

            freshMaterial.setCurrentQuantity(freshMaterial.getCurrentQuantity() - totalRequired);
            rawMaterialRepository.save(freshMaterial);
        }

        // 2. Record the Sale
        Sale sale = new Sale();
        sale.setRecipe(recipe);
        sale.setQuantity(request.getQuantity());
        sale.setTotalPrice(recipe.getSellingPrice() * request.getQuantity());
        sale.setSaleDate(LocalDateTime.now());

        saleRepository.save(sale);

        return ResponseEntity.ok(sale);
    }

    // ADD THIS NEW METHOD TO YOUR SALECONTROLLER
    @PostMapping("/bulk")
    @Transactional
    public ResponseEntity<?> recordBulkSale(@RequestBody java.util.List<SaleRequest> requests) {
        java.util.List<Sale> completedSales = new java.util.ArrayList<>();
        LocalDateTime transactionTime = LocalDateTime.now(); // Group them by the exact same time

        // 1. Deduct Inventory for ALL items first
        for (SaleRequest request : requests) {
            Recipe recipe = recipeRepository.findById(request.getRecipeId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            for (RecipeIngredient ingredient : recipe.getIngredients()) {
                RawMaterial freshMaterial = rawMaterialRepository.findById(ingredient.getRawMaterial().getItemNo())
                        .orElseThrow(() -> new RuntimeException("Material not found"));

                double totalRequired = ingredient.getQuantityRequired() * request.getQuantity();

                if (freshMaterial.getCurrentQuantity() < totalRequired) {
                    return ResponseEntity.badRequest().body("Insufficient stock for " + freshMaterial.getItemName());
                }

                freshMaterial.setCurrentQuantity(freshMaterial.getCurrentQuantity() - totalRequired);
                rawMaterialRepository.save(freshMaterial);
            }

            // 2. Record the Sale for this item
            Sale sale = new Sale();
            sale.setRecipe(recipe);
            sale.setQuantity(request.getQuantity());
            sale.setTotalPrice(recipe.getSellingPrice() * request.getQuantity());
            sale.setSaleDate(transactionTime);

            completedSales.add(saleRepository.save(sale));
        }
        return ResponseEntity.ok(completedSales);
    }
}