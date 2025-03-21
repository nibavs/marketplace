package com.marketplace.user_service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(UserDTO userDTO) {
        this.email = userDTO.email();
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.phoneNumber = userDTO.phoneNumber();
    }
}
