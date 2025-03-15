package com.CoWorkSpace.v1.api.Coworkingmanagment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity representing a space in the coworking management system.
 */
@Entity
@Table(name = "spaces")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevents issues with serialization
public class Space {

    /**
     * Unique identifier for the space.
     * Uses auto-incremented ID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the coworking space.
     */
    private String name;

    /**
     * Description of the coworking space.
     */
    private String description;
}
