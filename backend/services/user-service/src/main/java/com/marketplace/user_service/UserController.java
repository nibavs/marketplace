package com.marketplace.user_service;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;

    public UserController(UserRepository userRepository, KafkaTemplate<String, UserDTO> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
//        userRepository.save(user);
        kafkaTemplate.send("user-crud", user);
        return ResponseEntity.ok("User Created");
    }

}
