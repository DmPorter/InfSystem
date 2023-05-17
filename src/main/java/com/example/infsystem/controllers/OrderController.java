package com.example.infsystem.controllers;

import com.example.infsystem.forms.DateForm;
import com.example.infsystem.forms.OrderPositionWithComment;
import com.example.infsystem.helper.OrderForReport;
import com.example.infsystem.helper.OrdersList;
import com.example.infsystem.helper.SumOrders;
import com.example.infsystem.models.AdditiveOrderPosition;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.security.PersonDetails;
import com.example.infsystem.services.AdditiveService;
import com.example.infsystem.services.OrderService;
import com.example.infsystem.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AdditiveService additiveService;


    @Autowired
    private RecipeService recipeService;

    @GetMapping("/add/{id}")
    public String addNewRecipeIntoOrder(@PathVariable String id){
        return "redirect:/order/add/" + id + "/add-additives";
    }

    @GetMapping("/add/{id}/add-additives")
    public String addAdditivesInto(@PathVariable String id, Model model){

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setList(AdditiveOrderPosition.getList(additiveService.getAdditivesByRecipe(Long.parseLong(id))));

        model.addAttribute("id", id);

        model.addAttribute("orderPosition", new OrderPositionWithComment(orderPosition));
        return "order/addAdditives";
    }

    @PostMapping("/add/{id}/add-additives")
    public String addAdditivesIntoPost(@ModelAttribute("orderPosition") OrderPositionWithComment orderPosition,
                                       @PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        OrderPosition orderPositionNew = new OrderPosition();
        orderPositionNew.setList(AdditiveOrderPosition.getList(additiveService.getAdditivesByRecipe(Long.parseLong(id))));

        for(int i = 0; i < orderPosition.getOrderPosition().getList().size(); i++){
            orderPositionNew.getList().get(i).setQuantity(orderPosition.getOrderPosition().getList().get(i).getQuantity());
        }

        orderPositionNew.getList().removeIf(a -> a.getQuantity() == 0);

        orderPositionNew.setRecipe(recipeService.getRecipeById(Long.parseLong(id)));

        orderPositionNew.setQuantity(1);
        OrdersList.addNewOrderPosition(new OrderPositionWithComment(orderPositionNew, orderPosition.getComment()),
                personDetails);
        return "redirect:/recipe/all-recipe";
    }


    @GetMapping("/delete/{id}")
    public String deleteNewRecipeIntoOrder(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        OrdersList.deleteNewRecipe(Long.parseLong(id), personDetails);
        return "redirect:/order/order";
    }

    @GetMapping("/order")
    public String allOrder(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        var list = OrdersList.orderListMap.get(personDetails);
        if(list == null) list = new ArrayList<>();
        model.addAttribute("list", list);
        double sum = 0.0;
        for(OrderPositionWithComment value: list){
            OrderPosition val = value.getOrderPosition();
            sum += val.getQuantity() * val.getRecipe().getCost() + val.getCostAdditives();

        }
        model.addAttribute("sum", sum);
        return "order/orderList";
    }

    @GetMapping("/neworder")
    public String newOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        orderService.createNewOrder(personDetails);
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
