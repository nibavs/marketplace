package com.marketplace.customer;

import com.marketplace.customer.exception.CustomerCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public record CustomerController(CustomerService customerService) {

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.registerCustomer(customerDTO);
        } catch (Exception e) {
            throw new CustomerCreationException("Failed to save customer", e);
        }

        return ResponseEntity.ok("Customer Created");
    }

}
