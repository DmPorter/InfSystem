package com.example.infsystem.controllers;

import com.example.infsystem.forms.DateForm;
import com.example.infsystem.helper.OrderForReport;
import com.example.infsystem.helper.OrdersList;
import com.example.infsystem.helper.SumOrders;
import com.example.infsystem.services.OrderService;
import com.example.infsystem.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/add/{id}")
    public String addNewRecipeIntoOrder(@PathVariable String id){
        OrdersList.addNewRecipe(recipeService.getRecipeById(Long.parseLong(id)));
        return "redirect:/recipe/all-recipe";
    }

    @GetMapping("/order")
    public String allOrder(Model model){
        model.addAttribute("list", OrdersList.list);
        double sum = 0.0;
        for(var val: OrdersList.list){
            sum += val.getQuantity() * val.getRecipe().getCost();
        }
        model.addAttribute("sum", sum);
        return "order/orderList";
    }

    @GetMapping("/neworder")
    public String newOrder(){
        orderService.createNewOrder();
        return "order/newOrder";
    }

    @GetMapping("/report")
    public String allOrders(Model model){
        List<OrderForReport> list = OrderForReport.getOrders(orderService.getAllOrders());
        model.addAttribute("per", "Все время");
        model.addAttribute("list", list);

        model.addAttribute("sum", SumOrders.getSum(list));
        model.addAttribute("costPrice", SumOrders.getCostPrice(list));

        return "order/orders";
    }


    @GetMapping("/choose-date")
    public String chooseDate(Model model){
        model.addAttribute("report", new DateForm());
        return "order/formChooseDate";
    }


    @PostMapping("/report")
    public String reportByDate(@ModelAttribute("report") DateForm dateForm){
        return "redirect:/order/report-date?from=" + dateForm.getBegin().toString() + "&to=" + dateForm.getEnd().toString();
    }

    @GetMapping("/report-date")
    public String reportByDate(@RequestParam String from, @RequestParam String to, Model model){
        List<OrderForReport> list = OrderForReport.getOrders(orderService.getOrdersByDate(Timestamp.valueOf(from + " 0:0:01"), Timestamp.valueOf(to + " 23:59:59")));
        model.addAttribute("per", "с " + from + " по " + to);

        model.addAttribute("sum", SumOrders.getSum(list));
        model.addAttribute("costPrice", SumOrders.getCostPrice(list));
        model.addAttribute("list", list);
        return "order/orders";
    }


}
