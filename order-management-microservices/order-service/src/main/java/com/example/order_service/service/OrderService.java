package com.example.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;

@Service 
public class OrderService {
    private final OrderRepository orderRepository;

    OrderService (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public boolean deleteOrder(Long id){
        if (!orderRepository.existsById(id)) {
            return false;
        }

        orderRepository.deleteById(id);
        return true;

    }

}
