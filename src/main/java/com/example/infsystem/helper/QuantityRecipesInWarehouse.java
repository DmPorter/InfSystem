package com.example.infsystem.helper;

import com.example.infsystem.forms.OrderPositionWithComment;
import com.example.infsystem.models.Ingredient;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;
import com.example.infsystem.security.PersonDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuantityRecipesInWarehouse {

    public static Integer getQuantityByRecipe(Recipe recipe, PersonDetails personDetails) {
        List<Integer> quantities = new ArrayList<>();
        for (var ingredient : recipe.getIngredients()) {
            quantities.add((int)((ingredient.getProduct().getQuantityWarehouse()
                    - sumQuantityInOrderByProduct(ingredient.getProduct(),
                    personDetails)) / ingredient.getQuantity()));
        }
        return quantities.stream().min(Integer::compare).get();
    }



    public static ArrayList<RecipeWithQuantity> recipeDoubleMap(List<Recipe> list, PersonDetails personDetails){
        ArrayList<RecipeWithQuantity> arrayList = new ArrayList<>();
        for (var recipe: list){
            arrayList.add(new RecipeWithQuantity(recipe, getQuantityByRecipe(recipe, personDetails)));
        }
        return arrayList;
    }

//    public static int sumQuantityInOrderByRecipe(Recipe recipe, PersonDetails personDetails){
//        for(var val: OrdersList.list){
//            if(val.getRecipe().getIdRecipe() == recipe.getIdRecipe()) return val.getQuantity();
//        }
//        return 0;
//    }

    public static double sumQuantityInOrderByProduct(Product product, PersonDetails personDetails){
        double quantity = 0;

        if(OrdersList.orderListMap.get(personDetails) != null) {
            for (var val : OrdersList.orderListMap.get(personDetails)) {
                for (Ingredient product1 : val.getOrderPosition().getRecipe().getIngredients()) {
                    if (product1.getProduct().getIdProduct() == product.getIdProduct())
                        quantity += product1.getQuantity() * val.getOrderPosition().getQuantity();
                }
            }
        }
        return quantity;
    }
    public static Map<Product, Double> sumQuantityInOrderByProducts(List<OrderPositionWithComment> list){
        Map<Product, Double> map = new HashMap<>();

        for(var val: list){
            for (var product: val.getOrderPosition().getList()){
                if(map.containsKey(product.getAdditive().getProduct())){
                    map.put(product.getAdditive().getProduct(),
                            map.get(product.getAdditive().getProduct())
                            + product.getQuantity() * product.getAdditive().getQuantity());
                }else{
                    map.put(product.getAdditive().getProduct(),
                            (double) (product.getQuantity() * product.getAdditive().getQuantity()));
                }
            }
            for(Ingredient ingredient: val.getOrderPosition().getRecipe().getIngredients()){
                if(map.containsKey(ingredient.getProduct())){
                    map.put(ingredient.getProduct(), map.get(ingredient.getProduct())
                            + ingredient.getQuantity() * val.getOrderPosition().getQuantity());
                }else{
                    map.put(ingredient.getProduct(), ingredient.getQuantity() * val.getOrderPosition().getQuantity());
                }
            }
        }
        return map;
    }

}
