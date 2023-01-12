package com.example.infsystem.helper;

import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class OrdersList {
    public static List<OrderPosition> list = new ArrayList<>();

    public static void addNewRecipe(Recipe recipe){
        boolean flag = false;
        for(var val: list){
            if(val.getRecipe().getIdRecipe() == recipe.getIdRecipe()) {
                val.setQuantity(val.getQuantity() + 1);
                flag = true;
            }
        }

        if(!flag) list.add(new OrderPosition(1, recipe));
    }




}
