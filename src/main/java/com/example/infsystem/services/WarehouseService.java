package com.example.infsystem.services;

import com.example.infsystem.models.TypeProduct;
import com.example.infsystem.models.UnitMeasurement;
import com.example.infsystem.repositories.ProductRepository;
import com.example.infsystem.models.Product;
import com.example.infsystem.repositories.TypeProductRepository;
import com.example.infsystem.repositories.UnitMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final ProductRepository productRepository;
    private final UnitMeasureRepository unitMeasureRepository;
    private final TypeProductRepository typeProductRepository;


    @Autowired
    public WarehouseService(ProductRepository productRepository, UnitMeasureRepository unitMeasureRepository, TypeProductRepository typeProductRepository) {
        this.productRepository = productRepository;
        this.unitMeasureRepository = unitMeasureRepository;
        this.typeProductRepository = typeProductRepository;
    }

    public void updateProduct(Product product){
        productRepository.saveAndFlush(product);
    }
    public Product getProductByID(long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Product findProductById(long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public void deleteProductById(long id){
        productRepository.deleteById(id);
    }

    public void addNewProduct(Product product){
        productRepository.save(product);
    }

    public UnitMeasurement getUnitMeasureById(long id){
        Optional<UnitMeasurement> product = unitMeasureRepository.findById(id);
        return product.orElse(null);
    }

    public List<UnitMeasurement> getAllUnitMeasure(){
        return unitMeasureRepository.findAll();
    }

    public TypeProduct getTypeProductById(long id){
        Optional<TypeProduct> product = typeProductRepository.findById(id);
        return product.orElse(null);
    }
    public List<TypeProduct> getAllTypeProduct(){
        return typeProductRepository.findAll();
    }


}
