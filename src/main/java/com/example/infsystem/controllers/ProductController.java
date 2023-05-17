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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/{id}")
    public String aboutProduct(@PathVariable String id, Model model) {
        Product product = warehouseService.findProductById(Long.parseLong(id));
        model.addAttribute("product", product);

        return "product/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProductById(@PathVariable String id, Model model) {
        try {
            warehouseService.deleteProductById(Long.parseLong(id));
            model.addAttribute("id", id);
            return "product/deleteProduct";
        }catch (Exception e){
            return "product/excDeleteProduct";
        }
    }

    @GetMapping("/{id}/update")
    public String updateProductByID(@PathVariable long id, Model model){
        model.addAttribute("newProduct", warehouseService.getProductByID(id));
        model.addAttribute("typeProducts", warehouseService.getAllTypeProduct());
        model.addAttribute("typeMeasures", warehouseService.getAllUnitMeasure());
        model.addAttribute("product", warehouseService.getProductByID(id));
        model.addAttribute("id", id);
        return "product/updateProduct";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@ModelAttribute("newProduct") Product newProduct, @PathVariable long id){
        TypeProduct typeProduct = warehouseService.getTypeProductById(newProduct.getTypeProduct().getIdTypeProduct());
        UnitMeasurement unitMeasurement = warehouseService.getUnitMeasureById(newProduct.getUnitMeasurement().getIdUnit());
        newProduct.setIdProduct(id);
        newProduct.setUnitMeasurement(unitMeasurement);
        newProduct.setTypeProduct(typeProduct);

        newProduct.setName(warehouseService.getProductByID(id).getName());

        System.out.println(newProduct.getIdProduct());
        System.out.println(newProduct.getTypeProduct().getName());

        warehouseService.updateProduct(newProduct);

        return "redirect:/product/" + id;
    }
}
