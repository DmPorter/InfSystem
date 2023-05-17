package com.example.infsystem.services;

import com.example.infsystem.models.Additive;
import com.example.infsystem.repositories.AdditiveRepository;
import com.example.infsystem.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditiveService {

    private final AdditiveRepository additiveRepository;

    private final RecipeRepository recipeRepository;

    @Autowired
    public AdditiveService(AdditiveRepository additiveRepository, RecipeRepository recipeRepository) {
        this.additiveRepository = additiveRepository;
        this.recipeRepository = recipeRepository;
    }


    public List<Additive> getAdditivesByRecipe(long id) {
        return additiveRepository.findAdditivesByTypeRecipe(recipeRepository.findById(id).get().getTypeRecipe());
    }
}
