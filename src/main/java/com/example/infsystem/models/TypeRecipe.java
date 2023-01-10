package com.example.infsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "type_recipe")
public class TypeRecipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private long idType;

    @Column(name = "text")
    private String text;


    public TypeRecipe() {
    }

    public TypeRecipe(String text) {
        this.text = text;
    }

    public TypeRecipe(long idType, String text) {
        this.idType = idType;
        this.text = text;
    }

    public long getIdType() {
        return idType;
    }

    public void setIdType(long idType) {
        this.idType = idType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TypeRecipe{" +
                "idType=" + idType +
                ", text='" + text + '\'' +
                '}';
    }

}
