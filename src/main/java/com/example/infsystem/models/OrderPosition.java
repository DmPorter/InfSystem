package com.example.infsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "order_position")
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pos")
    private long idPos;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    @ManyToOne()
    @JoinColumn(name = "id_order")
    private Order order;

    public OrderPosition(long idPos, int quantity, Recipe recipe, Order order) {
        this.idPos = idPos;
        this.quantity = quantity;
        this.recipe = recipe;
        this.order = order;
    }

    public OrderPosition(int quantity, Recipe recipe, Order order) {
        this.quantity = quantity;
        this.recipe = recipe;
        this.order = order;
    }

    public OrderPosition() {

    }

    public long getIdPos() {
        return idPos;
    }

    public void setIdPos(long idPos) {
        this.idPos = idPos;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderPosition(int quantity, Recipe recipe) {
        this.quantity = quantity;
        this.recipe = recipe;
    }
}
