package com.example.order_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;

public class OrderServiceTest {

    @Test
    void shouldCreateOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        Order order = new Order(null, 1L, 1L, 2, "CREATED");

        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertEquals(order, result);
        verify(orderRepository).save(order);
    }

    @Test
    void shouldGetAllOrders() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        List<Order> orders = List.of(
                new Order(1L, 1L, 1L, 2, "CREATED"),
                new Order(2L, 2L, 2L, 1, "CREATED")
        );

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(2, result.size());
        verify(orderRepository).findAll();
    }

    @Test
    void shouldGetOrderById() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        Order order = new Order(1L, 1L, 1L, 2, "CREATED");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(orderRepository).findById(1L);
    }

    @Test
    void shouldReturnNullWhenOrderDoesNotExist() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        Order result = orderService.getOrderById(999L);

        assertNull(result);
        verify(orderRepository).findById(999L);
    }

    @Test
    void shouldDeleteOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        when(orderRepository.existsById(1L)).thenReturn(true);

        boolean result = orderService.deleteOrder(1L);

        assertTrue(result);
        verify(orderRepository).existsById(1L);
        verify(orderRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteOrderWhenOrderDoesNotExist() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);

        when(orderRepository.existsById(999L)).thenReturn(false);

        boolean result = orderService.deleteOrder(999L);

        assertFalse(result);
        verify(orderRepository).existsById(999L);
        verify(orderRepository, never()).deleteById(999L);
    }
}