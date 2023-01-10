package com.example.infsystem.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long idProduct;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "quantity")
    private double quantityWarehouse;

    @ManyToOne()
    @JoinColumn(name = "id_type_product")
    private TypeProduct typeProduct;


    @ManyToOne()
    @JoinColumn(name = "id_unit_measure")
    private UnitMeasurement unitMeasurement;

    @Column(name = "cost_price")
    private double cost;


    public UnitMeasurement getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(UnitMeasurement unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public Product(long idProduct, @NonNull String name, int quantityWarehouse,
                   TypeProduct typeProduct, UnitMeasurement unitMeasurement, double cost) {
        this.idProduct = idProduct;
        this.name = name;
        this.quantityWarehouse = quantityWarehouse;
        this.typeProduct = typeProduct;
        this.unitMeasurement = unitMeasurement;
        this.cost = cost;
    }

    public Product(@NonNull String name, int quantityWarehouse, TypeProduct typeProduct,
                   UnitMeasurement unitMeasurement, double cost) {
        this.name = name;
        this.quantityWarehouse = quantityWarehouse;
        this.typeProduct = typeProduct;
        this.unitMeasurement = unitMeasurement;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Product() {
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantityWarehouse() {
        return quantityWarehouse;
    }

    public void setQuantityWarehouse(double quantity) {
        this.quantityWarehouse = quantity;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", quantityWarehouse=" + quantityWarehouse +
                ", typeProduct=" + typeProduct +
                ", unitMeasurement=" + unitMeasurement +
                '}';
    }
}
