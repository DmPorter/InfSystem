package com.example.infsystem;

import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;
import com.example.infsystem.repositories.IngredientRepository;
import com.example.infsystem.repositories.ProductRepository;
import com.example.infsystem.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTests {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getRecipeById() {
        List<Product> product = productRepository.findAll();
        List<Recipe> recipes = recipeService.getAllRecipe()
                .stream()
                .filter((a) -> a.getIngredients() != null && a.getIngredients().size() != 0)
                .toList();
        Map<Recipe, Integer> map = new HashMap<>();
        for(var recipe : recipes){
            map.put(recipe, getQuantityByRecipe(recipe));
        }
        System.out.println();
    }

    public Integer getQuantityByRecipe(Recipe recipe) {
        List<Integer> quantities = new ArrayList<>();
        for (var ingredient : recipe.getIngredients()) {
            quantities.add((int)(ingredient.getProduct().getQuantityWarehouse() / ingredient.getQuantity()));
        }
        return quantities.stream().min(Integer::compare).get();
    }
}
