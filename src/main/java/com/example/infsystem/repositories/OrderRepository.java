package com.example.infsystem.repositories;

import com.example.infsystem.models.Order;
import com.example.infsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
