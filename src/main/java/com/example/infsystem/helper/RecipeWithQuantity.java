package com.example.infsystem.helper;

import com.example.infsystem.models.Recipe;

public class RecipeWithQuantity {
    private Recipe recipe;

    private int quantity;

    public RecipeWithQuantity(Recipe recipe, int quantity) {
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
