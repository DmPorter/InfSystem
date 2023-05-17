package com.example.infsystem.forms;

import com.example.infsystem.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PairRecipeInteger {

    private Recipe recipe;

    private int val;

    public PairRecipeInteger(Recipe recipe, int val) {
        this.recipe = recipe;
        this.val = val;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public static List<Point> toListPair(List<PairRecipeInteger> list){
        List<Point> resultList = new ArrayList<>();

        for(var value: list){
            resultList.add(new Point(value.recipe.getName(), value.val));
        }

        return resultList;
    }
}
