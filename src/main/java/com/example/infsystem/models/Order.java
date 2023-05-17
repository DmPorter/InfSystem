package com.example.infsystem.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long idOrder;

    @Column(name = "date")
    @CreationTimestamp
    private Timestamp date;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private List<OrderPosition> orderPositions;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Order() {

    }

    public Order(long idOrder, Timestamp date, List<OrderPosition> orderPositions, Person person) {
        this.idOrder = idOrder;
        this.date = date;
        this.orderPositions = orderPositions;
        this.person = person;
    }

    public Order(Timestamp date, List<OrderPosition> orderPositions, Person person) {
        this.date = date;
        this.orderPositions = orderPositions;
        this.person = person;
    }

    public Order(Timestamp date, Person person) {
        this.date = date;
        this.person = person;
    }

    public Order(Timestamp date) {
        this.date = date;
    }



    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public double getCost(){
        double sum = 0;

        for(OrderPosition value : orderPositions){
            sum += value.getQuantity() * value.getRecipe().getCost() + value.getCostAdditives();
        }

        return sum;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
