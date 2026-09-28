package com.example.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;

@Service 
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestClient userRestClient;
    private final RestClient productRestClient;

    public OrderService (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        this.userRestClient = RestClient.builder().baseUrl("http://localhost:8081").build();
        this.productRestClient = RestClient.builder().baseUrl("http://localhost:8082").build();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order){

        userRestClient.get().uri("/users/{id}",order.getUserId()).retrieve().toBodilessEntity();

        productRestClient.get().uri("/products/{id}",order.getProductId()).retrieve().toBodilessEntity();

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
