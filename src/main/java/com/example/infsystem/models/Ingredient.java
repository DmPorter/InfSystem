package com.example.infsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_record")
    private long idRecord;

    @ManyToOne()
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    @ManyToOne()
    private Product product;

    @Column(name = "quantity")
    private double quantity;


    public Ingredient() {
    }

    public Ingredient(Recipe recipe, Product product, int quantity) {
        this.recipe = recipe;
        this.product = product;
        this.quantity = quantity;
    }

    public Ingredient(long idRecord, Recipe recipe, Product product, int quantity) {
        this.idRecord = idRecord;
        this.recipe = recipe;
        this.product = product;
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public long getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(long idRecord) {
        this.idRecord = idRecord;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}