package com.example.infsystem;

import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.services.OrderService;
import com.example.infsystem.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RecipeService recipeService;

    @Test
    public void addNewOrder() {

        Order order = new Order(Timestamp.valueOf(LocalDateTime.now()));

        Order order1 = orderService.addNewOrder(order);

        List<OrderPosition> orderPositionList = new ArrayList<>();

        orderPositionList.add(new OrderPosition(1, recipeService.getRecipeById(1), order1));

        orderService.addNewOrderPositions(orderPositionList);
    }
}
