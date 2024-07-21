package com.strickland.ordersystem.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.strickland.ordersystem.kafka.OrderProducer;
import com.strickland.ordersystem.utils.Utils;
import com.strickland.ordersystem.entities.Order;
import com.strickland.ordersystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private OrderRepository orderRepository;

    public void processOrder(Order order) throws JsonProcessingException {
        final Order savedOrder = orderRepository.save(order);
        final String message = Utils.OBJECT_MAPPER.writeValueAsString(order);
        orderProducer.sendMessage(message);
    }
}