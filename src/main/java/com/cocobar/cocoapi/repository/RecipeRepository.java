package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIsActiveTrue();
    long countByIsActiveTrue();
}