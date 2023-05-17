package com.example.infsystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "id")
    private List<AdditiveOrderPosition> list;

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
        this.list = new ArrayList<>();
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

    public List<AdditiveOrderPosition> getList() {
        return list;
    }

    public void setList(List<AdditiveOrderPosition> list) {
        this.list = list;
    }

    public double getCostAdditives(){
        double sum = 0;

        for(AdditiveOrderPosition value: list){
            sum = value.getQuantity() * value.getAdditive().getQuantity() * value.getAdditive().getProduct().getCost();
        }

        return sum;
    }

    public String getAdditivesString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(AdditiveOrderPosition value: list){
            stringBuilder.append(value.getAdditive().getProduct().getName()).append(" ")
                    .append(value.getQuantity()*value.getAdditive().getQuantity()).append(" ")
                    .append(value.getAdditive().getProduct().getUnitMeasurement().getName())
                    .append("; ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return idPos == that.idPos && quantity == that.quantity && recipe.equals(that.recipe) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPos, quantity, recipe, order);
    }
}
