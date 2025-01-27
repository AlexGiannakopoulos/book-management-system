package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
}
