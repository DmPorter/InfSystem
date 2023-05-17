package com.example.infsystem.helper;

import com.example.infsystem.forms.Point;
import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;

import java.sql.Timestamp;
import java.util.*;

public class SumOrders {

    public static double getCostPrice(List<OrderForReport> list){
        double sum = 0;
        for(OrderForReport order: list){
            sum += order.getSum() - order.getCostPrice();
        }
        return sum;
    }

    public static double getSum(List<OrderForReport> list){
        double sum = 0;
        for(OrderForReport order: list){
            sum += order.getSum();
        }
        return sum;
    }

    public static double getSumOrder(Order order){
        double sum = 0;

        for(OrderPosition value: order.getOrderPositions()){
            sum += value.getQuantity() * value.getRecipe().getCost();
        }

        return sum;
    }

    public static List<Point> getSumCostOrders(List<Order> list){
        Map<String, Point> map = new HashMap<>();


        for(Order value: list){
            String date = value.getDate().toLocalDateTime().getDayOfMonth() + "-"
                    + value.getDate().toLocalDateTime().getMonthValue() + "-"
                    + value.getDate().toLocalDateTime().getYear();
            if(map.containsKey(date)) {
                map.get(date).setValue( map.get(date).getValue() + getSumOrder(value));
            }else{
                map.put(date, new Point(date, getSumOrder(value)));
            }
        }

        return map.values().stream().toList();
    }
}
