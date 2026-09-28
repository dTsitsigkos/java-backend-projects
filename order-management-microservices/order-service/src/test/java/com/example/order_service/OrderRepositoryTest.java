package com.example.order_service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;

@DataJpaTest
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveAndFindOrder() {
        Order order = new Order(null, 1L, 1L, 2, "CREATED");

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());

        Optional<Order> result = orderRepository.findById(savedOrder.getId());

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getUserId());
        assertEquals(1L, result.get().getProductId());
        assertEquals(2, result.get().getQuantity());
        assertEquals("CREATED", result.get().getStatus());
    }

    @Test
    void shouldFindAllOrders() {
        orderRepository.deleteAll();

        Order firstOrder = new Order(null, 1L, 1L, 2, "CREATED");

        Order secondOrder = new Order(null, 2L, 2L, 1, "CREATED");

        orderRepository.save(firstOrder);
        orderRepository.save(secondOrder);

        List<Order> orders = orderRepository.findAll();

        assertEquals(2, orders.size());
    }

    @Test
    void shouldDeleteOrder() {
        Order order = new Order(null, 1L, 1L, 2, "CREATED");

        Order savedOrder = orderRepository.save(order);

        Long id = savedOrder.getId();

        orderRepository.deleteById(id);

        Optional<Order> result = orderRepository.findById(id);

        assertTrue(result.isEmpty());
    }
}
