package com.example.infsystem.models;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="type_product")
public class TypeProduct{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_type_product")
        private long idTypeProduct;

        @Column(name = "name")
        private String name;


//        @OneToMany(mappedBy = "typeProduct", fetch = FetchType.LAZY)
//        private Collection<Product> products;


//        public Collection<Product> getProducts() {
//                return products;
//        }

//        public void setProducts(Collection<Product> products) {
//                this.products = products;
//        }

        public TypeProduct(String name, Collection<Product> products) {
                this.name = name;
                //this.products = products;
        }

        public TypeProduct(long idTypeProduct, String name, Collection<Product> products) {
                this.idTypeProduct = idTypeProduct;
                this.name = name;
                //this.products = products;
        }

        public TypeProduct() {
        }

        public long getIdTypeProduct() {
                return idTypeProduct;
        }

        public void setIdTypeProduct(long idTypeProduct) {
                this.idTypeProduct = idTypeProduct;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return "TypeProduct{" +
                        "idTypeProduct=" + idTypeProduct +
                        ", name='" + name + '\'' +
                        '}';
        }
}
