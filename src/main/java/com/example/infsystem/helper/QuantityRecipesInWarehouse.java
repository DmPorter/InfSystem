package com.example.infsystem.helper;

import com.example.infsystem.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuantityRecipesInWarehouse {

    public static Integer getQuantityByRecipe(Recipe recipe) {
        List<Integer> quantities = new ArrayList<>();
        for (var ingredient : recipe.getIngredients()) {
            quantities.add((int)(ingredient.getProduct().getQuantityWarehouse() / ingredient.getQuantity()));
        }
        return quantities.stream().min(Integer::compare).get();
    }

    public static Map<Recipe, Integer> recipeDoubleMap(List<Recipe> list){
        Map<Recipe, Integer> recipeDoubleMap = new HashMap<>();
        for (var recipe: list){
            recipeDoubleMap.put(recipe, getQuantityByRecipe(recipe));
        }
        return recipeDoubleMap;
    }

}
