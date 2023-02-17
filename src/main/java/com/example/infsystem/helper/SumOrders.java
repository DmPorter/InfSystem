package com.example.infsystem.helper;

import com.example.infsystem.models.Order;

import java.util.List;

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
}
