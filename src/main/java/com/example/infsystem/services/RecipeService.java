package com.example.infsystem.services;

import com.example.infsystem.models.Ingredient;
import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;
import com.example.infsystem.models.TypeRecipe;
import com.example.infsystem.repositories.IngredientRepository;
import com.example.infsystem.repositories.ProductRepository;
import com.example.infsystem.repositories.RecipeRepository;
import com.example.infsystem.repositories.TypeRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    private final IngredientRepository ingredientRepository;
    private final TypeRecipeRepository typeRecipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ProductRepository productRepository, IngredientRepository ingredientRepository, TypeRecipeRepository typeRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.typeRecipeRepository = typeRecipeRepository;
    }

    public Recipe saveRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public void saveIngredients(List<Ingredient> list){
        ingredientRepository.saveAll(list);
    }

    public Recipe getRecipeById(long id){
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> getAllRecipe(){
        return recipeRepository.findAll();
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public List<TypeRecipe> getAllTypeRecipe(){return typeRecipeRepository.findAll();}

    public void deleteRecipe(long id){
        ingredientRepository.deleteAll(ingredientRepository.findAll().stream().filter((a) -> a.getRecipe().getIdRecipe() == id).toList());
        recipeRepository.deleteById(id);
    }

    public Product getProductById(long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }
}
