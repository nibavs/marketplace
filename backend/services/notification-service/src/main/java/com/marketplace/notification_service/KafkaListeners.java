package com.marketplace.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "user-crud",
            groupId = "user-service"
    )
    public void listen(UserDTO user) {
        System.out.println("User: " + user.email() + " has been created!");
        System.out.println("Name - " + user.firstName() + " " + user.lastName());
        System.out.println("Phone - " + user.phoneNumber());
    }
}
