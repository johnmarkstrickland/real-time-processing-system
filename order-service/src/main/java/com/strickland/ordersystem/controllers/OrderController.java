package com.strickland.ordersystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.strickland.ordersystem.services.OrderService;
import com.strickland.ordersystem.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(name = "/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) throws JsonProcessingException {
        orderService.processOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }
}