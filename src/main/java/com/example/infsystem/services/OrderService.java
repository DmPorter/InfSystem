package com.example.infsystem.services;

import com.example.infsystem.helper.OrdersList;
import com.example.infsystem.helper.QuantityRecipesInWarehouse;
import com.example.infsystem.models.Order;
import com.example.infsystem.models.OrderPosition;
import com.example.infsystem.models.Product;
import com.example.infsystem.repositories.OrderPositionRepository;
import com.example.infsystem.repositories.OrderRepository;
import com.example.infsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
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

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderPositionRepository = orderPositionRepository;
    }


    public Order addNewOrder(Order order){
        return orderRepository.save(order);
    }

    public void addNewOrderPositions(List<OrderPosition> list){
        orderPositionRepository.saveAll(list);
    }


    public void createNewOrder(){
        Order order = new Order(Timestamp.valueOf(LocalDateTime.now()));
        addNewOrder(order);

        for(var val: OrdersList.list){
            val.setOrder(order);
        }

        addNewOrderPositions(OrdersList.list);

        Map<Product, Double> map =  QuantityRecipesInWarehouse.sumQuantityInOrderByProducts(OrdersList.list);

        List<Product> products = productRepository.findAll();

        for(Product product: products){
            if(map.containsKey(product)){
                product.setQuantityWarehouse(product.getQuantityWarehouse() - map.get(product));
            }
        }

        productRepository.saveAll(products);
        OrdersList.list = new ArrayList<>();
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
