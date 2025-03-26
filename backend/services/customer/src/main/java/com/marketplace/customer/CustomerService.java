package com.marketplace.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, KafkaTemplate<String, CustomerDTO> kafkaTemplate) {
    public void registerCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .email(customerDTO.email())
                .phoneNumber(customerDTO.phoneNumber())
                .build();

        // todo: check if email is valid
        // todo: check if email is not taken
        customerRepository.save(customer);
        log.info("Customer registered: {}", customer);
        kafkaTemplate.send("customer", customerDTO);
    }
}
