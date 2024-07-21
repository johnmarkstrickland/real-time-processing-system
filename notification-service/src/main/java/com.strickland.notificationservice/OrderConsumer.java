package com.strickland.notificationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "OrderPlaced", groupId = "notification-group")
    public void consumeOrder(String message) {
        // Logic to process the order message and send notification
        notificationService.sendNotification("Order placed: " + message);
    }
}