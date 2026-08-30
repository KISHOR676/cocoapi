package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.model.Recipe;
import com.cocobar.cocoapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository repository;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        // Saves the recipe and all linked ingredients simultaneously
        return repository.save(recipe);
    }

    // UPDATE RECIPE
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        Recipe existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        existing.setName(updatedRecipe.getName());
        existing.setSellingPrice(updatedRecipe.getSellingPrice());
        existing.setImageUrl(updatedRecipe.getImageUrl());

        // This clears old ingredients and saves the new ones cleanly
        existing.getIngredients().clear();
        existing.getIngredients().addAll(updatedRecipe.getIngredients());

        return repository.save(existing);
    }

    // DELETE RECIPE
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);
    }
}