package com.strickland.notificationservice;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(String message) {
        // Logic to send notification (e.g., email, SMS, etc.)
        System.out.println("Sending notification: " + message);
    }
}