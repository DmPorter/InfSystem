package com.example.infsystem.helper;

import com.example.infsystem.forms.PairRecipeInteger;
import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPopularityRecipe {

    public static List<PairRecipeInteger> getPopularityRecipes(List<Order> list){
        Map<Recipe, PairRecipeInteger> map = new HashMap<>();

        for(Order order: list){
            for(OrderPosition orderPosition: order.getOrderPositions()){
                if(map.containsKey(orderPosition.getRecipe())) {
                    map.get(orderPosition.getRecipe()).setVal(map.get(orderPosition.getRecipe()).getVal()
                                                                        + orderPosition.getQuantity());
                }else{
                    map.put(orderPosition.getRecipe(), new PairRecipeInteger(orderPosition.getRecipe(), orderPosition.getQuantity()));
                }
            }
        }

        return map.values().stream().toList();
    }
}
