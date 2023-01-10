package com.example.infsystem;

import com.example.infsystem.models.Product;
import com.example.infsystem.services.WarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseServiceUnitTest {
    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void getAllProduct(){
        List<Product> list = warehouseService.list();

        System.out.println(list);
    }

    @Test
    public void getProductById(){
        Product product = warehouseService.findProductById(1);

        System.out.println(product);
    }

    @Test
    public void getAllMeasure(){
        System.out.println(warehouseService.getAllUnitMeasure());
    }

    @Test
    public void getAllTypeProduct(){
        System.out.println(warehouseService.getAllTypeProduct());
    }
}
