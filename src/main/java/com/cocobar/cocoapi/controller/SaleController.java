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
}








//package com.cocobar.cocoapi.controller;
//
//import com.cocobar.cocoapi.model.*;
//import com.cocobar.cocoapi.repository.InventoryRepository;
//import com.cocobar.cocoapi.repository.RawMaterialRepository; // THIS WAS THE MISSING LINE!
//import com.cocobar.cocoapi.repository.RecipeRepository;
//import com.cocobar.cocoapi.repository.SaleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/api/sales")
//public class SaleController {
//
//    @Autowired
//    private SaleRepository saleRepository;
//
//    @Autowired
//    private RecipeRepository recipeRepository;
//
//    @Autowired
//    private RawMaterialRepository rawMaterialRepository;
//
//    @PostMapping
//    @Transactional // Ensures if one part fails (like out of stock), the whole transaction rolls back safely
//    public ResponseEntity<?> recordSale(@RequestBody SaleRequest request) {
//        Recipe recipe = recipeRepository.findById(request.getRecipeId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        // 1. Deduct Inventory based on the recipe logic
//        for (RecipeIngredient ingredient : recipe.getIngredients()) {
//            RawMaterial material = ingredient.getRawMaterial();
//            double totalRequired = ingredient.getQuantityRequired() * request.getQuantity();
//
//            if (material.getCurrentQuantity() < totalRequired) {
//                return ResponseEntity.badRequest().body("Insufficient stock for " + material.getItemName());
//            }
//
//            material.setCurrentQuantity(material.getCurrentQuantity() - totalRequired);
//            rawMaterialRepository.save(material);
//        }
//
//        // 2. Record the Sale
//        Sale sale = new Sale();
//        sale.setRecipe(recipe);
//        sale.setQuantity(request.getQuantity());
//        sale.setTotalPrice(recipe.getSellingPrice() * request.getQuantity());
//        sale.setSaleDate(LocalDateTime.now());
//
//        saleRepository.save(sale);
//
//        return ResponseEntity.ok(sale);
//    }
//}