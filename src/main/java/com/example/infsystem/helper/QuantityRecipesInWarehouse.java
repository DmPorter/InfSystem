package com.example.infsystem.helper;

import com.example.infsystem.models.Ingredient;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuantityRecipesInWarehouse {

    public static Integer getQuantityByRecipe(Recipe recipe) {
        List<Integer> quantities = new ArrayList<>();
        for (var ingredient : recipe.getIngredients()) {
            quantities.add((int)((ingredient.getProduct().getQuantityWarehouse() - sumQuantityInOrderByProduct(ingredient.getProduct())) / ingredient.getQuantity()));
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

    public static int sumQuantityInOrderByRecipe(Recipe recipe){
        for(var val: OrdersList.list){
            if(val.getRecipe().getIdRecipe() == recipe.getIdRecipe()) return val.getQuantity();
        }
        return 0;
    }

    public static double sumQuantityInOrderByProduct(Product product){
        double quantity = 0;

        for(var val: OrdersList.list){
           for(Ingredient product1: val.getRecipe().getIngredients()){
               if(product1.getProduct().getIdProduct() == product.getIdProduct()) quantity += product1.getQuantity() * val.getQuantity();
           }
        }
        return quantity;
    }
    public static Map<Product, Double> sumQuantityInOrderByProducts(List<OrderPosition> list){
        Map<Product, Double> map = new HashMap<>();

        for(var val: list){
            for(Ingredient ingredient: val.getRecipe().getIngredients()){
                if(map.containsKey(ingredient.getProduct())){
                    map.put(ingredient.getProduct(), map.get(ingredient.getProduct())
                            + ingredient.getQuantity() * val.getQuantity());
                }else{
                    map.put(ingredient.getProduct(), ingredient.getQuantity() * val.getQuantity());
                }
            }
        }
        return map;
    }

}
