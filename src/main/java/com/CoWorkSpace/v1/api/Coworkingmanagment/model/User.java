package com.CoWorkSpace.v1.api.Coworkingmanagment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a user in the coworking management system.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevents issues with serialization
public class User {

    /**
     * Unique identifier for the user.
     * Uses auto-incremented ID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Email of the user.
     * Must be unique.
     */
    @Column(unique = true)
    private String email;

    /**
     * Hashed password of the user.
     */
    private String password;

    /**
     * Custom builder method for creating a new user instance.
     *
     * @param name     User's name
     * @param email    User's email
     * @param password User's password
     */
    @Builder(builderMethodName = "newUserBuilder")
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
