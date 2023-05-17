package com.example.infsystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderPosition")
@Entity
@Table(name = "additive_order_position")
public class AdditiveOrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_additive")
    private Additive additive;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order_position")
    private OrderPosition orderPosition;


    public AdditiveOrderPosition() {
    }

    public AdditiveOrderPosition(long id, Additive additive, int quantity, OrderPosition orderPosition) {
        this.id = id;
        this.additive = additive;
        this.quantity = quantity;
        this.orderPosition = orderPosition;
    }

    public AdditiveOrderPosition(Additive additive, int quantity) {
        this.additive = additive;
        this.quantity = quantity;
    }

    public static ArrayList<AdditiveOrderPosition> getList(List<Additive> list) {
        ArrayList<AdditiveOrderPosition> arrayList = new ArrayList<>();

        for(var val: list){
            arrayList.add(new AdditiveOrderPosition(val, 0));
        }

        return arrayList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Additive getAdditive() {
        return additive;
    }

    public void setAdditive(Additive additive) {
        this.additive = additive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderPosition getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

}
