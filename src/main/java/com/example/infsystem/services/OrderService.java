package com.example.infsystem.services;

import com.example.infsystem.forms.OrderPositionWithComment;
import com.example.infsystem.helper.OrdersList;
import com.example.infsystem.helper.QuantityRecipesInWarehouse;
import com.example.infsystem.models.AdditiveOrderPosition;
import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Product;
import com.example.infsystem.repositories.*;
import com.example.infsystem.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderPositionRepository orderPositionRepository;

    private final AdditiveOrderPositionRepository additiveOrderPositionRepository;

    private final PersonRepository personRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        OrderPositionRepository orderPositionRepository,
                        AdditiveOrderPositionRepository additiveOrderPositionRepository,
                        PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.additiveOrderPositionRepository = additiveOrderPositionRepository;
        this.personRepository = personRepository;
    }


    public Order addNewOrder(Order order){
        return orderRepository.save(order);
    }

    public List<OrderPosition> addNewOrderPositions(List<OrderPosition> list){
        return orderPositionRepository.saveAllAndFlush(list);
    }


    public void createNewOrder(PersonDetails personDetails){
        Order order = new Order(Timestamp.valueOf(LocalDateTime.now()));
        order.setPerson(personRepository.findByUsername(personDetails.getUsername()).get());
        addNewOrder(order);

        List<OrderPositionWithComment> list = OrdersList.orderListMap.get(personDetails);
//        ArrayList<AdditiveOrderPosition> additiveOrderPositions = new ArrayList<>();

        for(var value: list){
            var val = value.getOrderPosition();
            val.setOrder(order);

            List<AdditiveOrderPosition> additiveOrderPositions = val.getList();
            val.setList(new ArrayList<>());
            OrderPosition orderPosition = orderPositionRepository.saveAndFlush(val);

            for(AdditiveOrderPosition additiveOrderPosition: additiveOrderPositions){
                additiveOrderPosition.setOrderPosition(orderPosition);
            }

            additiveOrderPositionRepository.saveAllAndFlush(additiveOrderPositions);
        }

//        addNewOrderPositions(list);

        Map<Product, Double> map =  QuantityRecipesInWarehouse.sumQuantityInOrderByProducts(list);

        List<Product> products = productRepository.findAll();

        for(Product product: products){
            if(map.containsKey(product)){
                product.setQuantityWarehouse(product.getQuantityWarehouse() - map.get(product));
            }
        }

        productRepository.saveAll(products);
        OrdersList.orderListMap.remove(personDetails);

    }

    public List<Order> getTodayOrders(){
        Timestamp startDate = Timestamp.valueOf(LocalDateTime.now());
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(1);
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.now());
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        return orderRepository.findByDateBetween(startDate, endDate);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByDate(Timestamp from, Timestamp to){
        List<Order> orders = orderRepository.findAll();

        orders.removeIf((a) -> !from.before(a.getDate()) || !to.after(a.getDate()));

        return orders;
    }
}
