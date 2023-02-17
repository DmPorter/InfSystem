package com.example.infsystem.controllers;

import com.example.infsystem.forms.DateForm;
import com.example.infsystem.helper.OrderForReport;
import com.example.infsystem.helper.SumOrders;
import com.example.infsystem.models.Person;
import com.example.infsystem.services.OrderService;
import com.example.infsystem.services.RecipeService;
import com.example.infsystem.services.UserService;
import com.example.infsystem.services.WarehouseService;
import com.example.infsystem.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/main")
    public String mainPage(Model model){
        List<OrderForReport> orderForReportList = OrderForReport.getOrders(orderService.getTodayOrders());
        model.addAttribute("list", orderForReportList);
        model.addAttribute("count", orderForReportList.size());
        model.addAttribute("sum", SumOrders.getSum(orderForReportList));
        model.addAttribute("cost", SumOrders.getCostPrice(orderForReportList));
        return "admin/main";
    }

    @GetMapping("/choose-date")
    public String chooseDate(Model model) {
        model.addAttribute("report", new DateForm());
        return "admin/chooseDateForOrders";
    }

    @PostMapping("/report")
    public String reportByDate(@ModelAttribute("report") DateForm dateForm){
        return "redirect:/admin/report-date?from=" + dateForm.getBegin().toString() + "&to=" + dateForm.getEnd().toString();
    }

    @GetMapping("/report-date")
    public String reportByDate(@RequestParam String from, @RequestParam String to, Model model){
        List<OrderForReport> list = OrderForReport.getOrders(orderService.getOrdersByDate(Timestamp.valueOf(from + " 0:0:01"), Timestamp.valueOf(to + " 23:59:59")));
        model.addAttribute("per", "с " + from + " по " + to);

        model.addAttribute("sum", SumOrders.getSum(list));
        model.addAttribute("costPrice", SumOrders.getCostPrice(list));
        model.addAttribute("list", list);
        return "admin/orders";
    }

    @GetMapping("/product")
    public String getAllProducts(Model model){
        model.addAttribute("list", recipeService.getAllProduct());
        return "admin/products";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "admin/registration";
    }

    @PostMapping("/auth/registration")
    public String performRegistration(@ModelAttribute("person") Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "redirect:/admin/registration";
        }
        userService.register(person);

        return "redirect:/admin/main";
    }
}
