package com.marketplace.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "customer",
            groupId = "customer"
    )
    public void listen(CustomerDTO customer) {
        System.out.println("Customer: " + customer.email() + " has been created!");
        System.out.println("Name - " + customer.firstName() + " " + customer.lastName());
        System.out.println("Phone - " + customer.phoneNumber());
    }
}
