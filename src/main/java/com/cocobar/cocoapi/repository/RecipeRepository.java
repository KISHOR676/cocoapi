package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}