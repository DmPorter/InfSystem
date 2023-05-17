package com.example.infsystem.repositories;

import com.example.infsystem.models.Order;
import com.example.infsystem.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> getRecipesByIngredientsIsNotNullOrderByIdRecipe();
}
