package com.example.infsystem.helper;

import com.example.infsystem.forms.OrderPositionWithComment;
import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Person;
import com.example.infsystem.models.Recipe;
import com.example.infsystem.security.PersonDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersList {

    public static Map<PersonDetails, List<OrderPositionWithComment>> orderListMap = new HashMap<>();



    public static void addNewOrderPosition(OrderPositionWithComment orderPosition, PersonDetails person){
        if (orderListMap.containsKey(person)) orderListMap.get(person).add(orderPosition);
        else {
            ArrayList<OrderPositionWithComment> list = new ArrayList<>();
            list.add(orderPosition);
            orderListMap.put(person, list);
        }
    }

    public static void deleteNewRecipe(long id, PersonDetails person) {
        orderListMap.get(person).remove((int)id);
    }




}
