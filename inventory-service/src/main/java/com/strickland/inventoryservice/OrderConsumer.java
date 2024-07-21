package com.strickland.inventoryservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "OrderPlaced", groupId = "inventory-group")
    public void consumeOrder(String message) {
        System.out.println("Order Placed: " + message);
        try {
            final JsonNode jsonNode = objectMapper.readTree(message);
            jsonNode.get("items")
                    .forEach(item -> {
                        final String productId = item.get("productId")
                                                 .asText();
                        final int quantity = item.get("quantity")
                                           .asInt();
                        inventoryService.updateInventory(productId, quantity);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}