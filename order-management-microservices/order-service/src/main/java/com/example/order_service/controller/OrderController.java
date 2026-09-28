package com.example.order_service.controller;

import com.example.order_service.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController (OrderService orderService, OrderRepository orderRepository){
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable  Long id) {
        Order order = orderService.getOrderById(id);

        if (order == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createOrder = orderService.createOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
    }
    
 
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        boolean deleted = orderService.deleteOrder(id);

        if (deleted == false){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }

}
