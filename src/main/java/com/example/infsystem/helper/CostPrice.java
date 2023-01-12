package com.example.infsystem.helper;

import com.example.infsystem.models.Ingredient;

import java.util.List;

public class CostPrice {

    public static double getCostPrice(List<Ingredient> ingredientList){
        double cost = 0;

        for(var val: ingredientList){
            cost += val.getProduct().getCost() * val.getQuantity();
        }
        return cost;
    }
}
