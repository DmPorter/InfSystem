package com.example.infsystem.helper;

import com.example.infsystem.models.Product;
import com.example.infsystem.models.Recipe;

import java.util.List;

public class ProductsCreationDto {
    private List<Product> list;
    private Recipe recipe;

    public ProductsCreationDto() {
    }

    public ProductsCreationDto(List<Product> list) {
        this.list = list;
    }

    public ProductsCreationDto(List<Product> list, Recipe recipe) {
        this.list = list;
        this.recipe = recipe;
    }

    public void addProduct(Product product){
        list.add(product);
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
