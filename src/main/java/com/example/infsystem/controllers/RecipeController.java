package com.example.infsystem.controllers;

import com.example.infsystem.helper.CostPrice;
import com.example.infsystem.helper.ProductsCreationDto;
import com.example.infsystem.helper.QuantityRecipesInWarehouse;
import com.example.infsystem.models.Ingredient;
import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;
import com.example.infsystem.security.PersonDetails;
import com.example.infsystem.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/new-recipe")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "/recipe/newRecipeOne";
    }

    @PostMapping("/new-recipe")
    public String newRecipeAndIngr(@ModelAttribute Recipe recipe, Model model){

        Recipe newRecipe = recipeService.saveRecipe(new Recipe(recipe.getName(), recipe.getCost(), recipeService.getAllTypeRecipe().get(0)));

        ArrayList<Product> products = new ArrayList<>();
        for(int i = 0; i < recipe.getIdRecipe(); i++){
            products.add(new Product());
        }

        ProductsCreationDto form = new ProductsCreationDto(products, recipe);

        model.addAttribute("recipe", recipe);
        model.addAttribute("form", form);
        model.addAttribute("listProduct", recipeService.getAllProduct());
        model.addAttribute("idRecipe", newRecipe.getIdRecipe());
        return "/recipe/newRecipeTwo";
    }

    @PostMapping("/save/{id}")
    public String newRecipe(@ModelAttribute ProductsCreationDto productsCreationDto, @PathVariable long id){
        System.out.println();
        List<Ingredient> list = new ArrayList<>();
        for(var value: productsCreationDto.getList()){
            list.add(new Ingredient(recipeService.getRecipeById(id), recipeService.getProductById(value.getIdProduct()), (int) value.getQuantityWarehouse()));
        }
        recipeService.saveIngredients(list);
        return "redirect:/recipe/all-recipe";
    }

    @GetMapping("/all-recipe")
    public String allRecipe(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        List<Recipe> list = recipeService.getAllRecipe();
        model.addAttribute("recipes", QuantityRecipesInWarehouse.recipeDoubleMap(list, personDetails));
        return "/recipe/allRecipe";
    }

    @GetMapping("/{id}")
    public String recipeById(@PathVariable long id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        model.addAttribute("costPrice", CostPrice.getCostPrice(recipeService.getRecipeById(id).getIngredients()));
        return "/recipe/recipe";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable long id){
        recipeService.deleteRecipe(id);
        return "recipe/deleteRecipe";
    }




}
