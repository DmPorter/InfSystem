package com.example.infsystem.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private long idRecipe;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @ManyToOne()
    @JoinColumn(name = "type_recipe_id_type")
    private TypeRecipe typeRecipe;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_recipe")
    private List<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String name, double cost, TypeRecipe typeRecipe) {
        this.name = name;
        this.cost = cost;
        this.typeRecipe = typeRecipe;
    }

    public Recipe(long idRecipe, String name, double cost, TypeRecipe typeRecipe) {
        this.idRecipe = idRecipe;
        this.name = name;
        this.cost = cost;
        this.typeRecipe = typeRecipe;
    }

    public Recipe(String name, double cost, TypeRecipe typeRecipe, List<Ingredient> ingredients) {
        this.name = name;
        this.cost = cost;
        this.typeRecipe = typeRecipe;
        this.ingredients = ingredients;
    }

    public Recipe(long idRecipe, String name, double cost, TypeRecipe typeRecipe, List<Ingredient> ingredients) {
        this.idRecipe = idRecipe;
        this.name = name;
        this.cost = cost;
        this.typeRecipe = typeRecipe;
        this.ingredients = ingredients;
    }

    public long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public TypeRecipe getTypeRecipe() {
        return typeRecipe;
    }

    public void setTypeRecipe(TypeRecipe typeRecipeIdType) {
        this.typeRecipe = typeRecipeIdType;
    }




}
