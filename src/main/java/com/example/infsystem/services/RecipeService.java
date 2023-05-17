package com.example.infsystem.services;

import com.example.infsystem.models.*;
import com.example.infsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    private final IngredientRepository ingredientRepository;
    private final TypeRecipeRepository typeRecipeRepository;

    private final ProviderRepository providerRepository;



    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ProductRepository productRepository, IngredientRepository ingredientRepository, TypeRecipeRepository typeRecipeRepository, ProviderRepository providerRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.typeRecipeRepository = typeRecipeRepository;
        this.providerRepository = providerRepository;
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
        Map<Long, Recipe> map = new HashMap<>();
        for(var val: recipeRepository.getRecipesByIngredientsIsNotNullOrderByIdRecipe()){
            map.put(val.getIdRecipe(), val);
        }

        ArrayList<Recipe> list = new ArrayList<>(map.values());

        list.sort(Comparator.comparingLong(Recipe::getIdRecipe));
        return list;
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

    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }

    public void addNewProvider(Provider provider){
        providerRepository.save(provider);
    }

}
