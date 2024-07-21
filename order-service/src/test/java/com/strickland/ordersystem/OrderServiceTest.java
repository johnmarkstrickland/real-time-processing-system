package com.strickland.ordersystem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.strickland.ordersystem.entities.Order;
import com.strickland.ordersystem.entities.OrderItem;
import com.strickland.ordersystem.enums.OrderStatus;
import com.strickland.ordersystem.kafka.OrderProducer;
import com.strickland.ordersystem.repositories.OrderRepository;
import com.strickland.ordersystem.services.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderProducer orderProducer;

    @Mock
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        OrderItem item1 = new OrderItem(1L, 2, new BigDecimal("19.99"));
        OrderItem item2 = new OrderItem(2L, 1, new BigDecimal("49.99"));

        order = new Order(12345L, Arrays.asList(item1, item2), new BigDecimal("89.97"), OrderStatus.NEW, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testProcessOrder() throws JsonProcessingException {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.processOrder(order);

        verify(orderRepository).save(any(Order.class));
        verify(orderProducer).sendMessage(any(String.class));
    }
}
