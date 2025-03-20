package com.marketplace.user_service;

import com.marketplace.user_service.exception.UserCreationException;
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
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserCreationException("Failed to save user", e);
        }

        kafkaTemplate.send("user-crud", userDTO);

        return ResponseEntity.ok("User Created");
    }

}
