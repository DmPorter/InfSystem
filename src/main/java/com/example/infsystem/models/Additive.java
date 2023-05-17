package com.example.infsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "additive")
public class Additive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "id_type_recipe")
    private TypeRecipe typeRecipe;

    private int quantity;


    public Additive() {
    }

    public Additive(long id, Product product, TypeRecipe typeRecipe, int quantity) {
        this.id = id;
        this.product = product;
        this.typeRecipe = typeRecipe;
        this.quantity = quantity;
    }

    public Additive(Product product, TypeRecipe typeRecipe, int quantity) {
        this.product = product;
        this.typeRecipe = typeRecipe;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public TypeRecipe getTypeRecipe() {
        return typeRecipe;
    }

    public void setTypeRecipe(TypeRecipe typeRecipe) {
        this.typeRecipe = typeRecipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
