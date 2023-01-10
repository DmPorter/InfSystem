package com.example.infsystem.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "unit_measurement")
public class UnitMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unit")
    private long IdUnit;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "unitMeasurement", fetch = FetchType.LAZY)
//    private Collection<Product> products;

//    public Collection<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Collection<Product> products) {
//        this.products = products;
//    }

    public UnitMeasurement(String name) {
        this.name = name;
        //this.products = products;
    }

    public UnitMeasurement(long idUnit, String name) {
        IdUnit = idUnit;
        this.name = name;
        //this.products = products;
    }

    public UnitMeasurement() {
    }

    public long getIdUnit() {
        return IdUnit;
    }

    public void setIdUnit(long idUnit) {
        IdUnit = idUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UnitMeasurement{" +
                "IdUnit=" + IdUnit +
                ", name='" + name + '\'' +
                '}';
    }
}
