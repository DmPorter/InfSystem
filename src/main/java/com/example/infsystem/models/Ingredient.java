package com.example.infsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_record")
    private long idRecord;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recipe")
    private TypeRecipe idType;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "quantity")
    private int quantity;


    public Ingredient() {
    }

    public Ingredient(TypeRecipe idType, int quantity, Product product) {
        this.idType = idType;
        this.quantity = quantity;
        this.product = product;
    }

    public Ingredient(long idRecord, TypeRecipe idType, int quantity, Product product) {
        this.idRecord = idRecord;
        this.idType = idType;
        this.quantity = quantity;
        this.product = product;
    }

    public long getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(long idRecord) {
        this.idRecord = idRecord;
    }

    public TypeRecipe getIdType() {
        return idType;
    }

    public void setIdType(TypeRecipe typeRecipe) {
        this.idType = typeRecipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idRecord=" + idRecord +
                ", typeRecipe=" + idType.getIdType() +
                ", product=" + product.getIdProduct() +
                ", quantity=" + quantity +
                '}';
    }
}