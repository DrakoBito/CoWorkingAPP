package com.CoWorkSpace.v1.api.Coworkingmanagment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity representing a reservation in the coworking space system.
 */
@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Reservation {

    /**
     * Unique identifier for the reservation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User associated with the reservation.
     * Uses lazy loading to optimize performance.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Space associated with the reservation.
     * Uses lazy loading to optimize performance.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    /**
     * Date of the reservation.
     */
    private LocalDate date;

    /**
     * Start time of the reservation.
     */
    private LocalTime startTime;

    /**
     * Custom builder method to create a new reservation.
     */
    @Builder(builderMethodName = "newReservationBuilder")
    public Reservation(User user, Space space, LocalDate date, LocalTime startTime) {
        this.user = user;
        this.space = space;
        this.date = date;
        this.startTime = startTime;
    }
}
