package com.example.infsystem.controllers;

import com.example.infsystem.models.Product;
import com.example.infsystem.models.TypeProduct;
import com.example.infsystem.models.UnitMeasurement;
import com.example.infsystem.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/all")
    public String getAllProductInWarehouse(Model model) {
        model.addAttribute("products", warehouseService.list());
        return "warehouse/allInWarehouse";
    }

    @GetMapping("/new-product")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product(0, "", 0, new TypeProduct(), new UnitMeasurement(), 0));
        model.addAttribute("typeProducts", warehouseService.getAllTypeProduct());
        model.addAttribute("typeMeasures", warehouseService.getAllUnitMeasure());
        return "warehouse/newProduct";
    }

    @PostMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        TypeProduct typeProduct = warehouseService.getTypeProductById(product.getTypeProduct().getIdTypeProduct());
        UnitMeasurement unitMeasurement = warehouseService.getUnitMeasureById(product.getUnitMeasurement().getIdUnit());
        Product product1 = new Product(0, product.getName(), 0, typeProduct, unitMeasurement, product.getCost());
        warehouseService.addNewProduct(product1);
        return "redirect:/warehouse/all";
    }


}
