package com.example.infsystem.controllers;

import com.example.infsystem.forms.PairRecipeInteger;
import com.example.infsystem.forms.Point;
import com.example.infsystem.helper.CountPopularityRecipe;
import com.example.infsystem.helper.OrderForReport;
import com.example.infsystem.helper.SumOrders;
import com.example.infsystem.models.Order;
import com.example.infsystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final OrderService orderService;

    @Autowired
    public RESTController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/report-date")
    public List<Point> getDataForOrdersByCost(@RequestParam String from, @RequestParam String to){
        List<Order> list = orderService.getOrdersByDate(Timestamp.valueOf(from + " 0:0:01"),
                Timestamp.valueOf(to + " 23:59:59"));

        return SumOrders.getSumCostOrders(list);
    }

    @GetMapping("/report-popularity")
    public List<Point> getDataForOrdersByPop(@RequestParam String from, @RequestParam String to){
        List<Order> list = orderService.getOrdersByDate(Timestamp.valueOf(from + " 0:0:01"),
                Timestamp.valueOf(to + " 23:59:59"));


        return PairRecipeInteger.toListPair(CountPopularityRecipe.getPopularityRecipes(list));
    }
}
