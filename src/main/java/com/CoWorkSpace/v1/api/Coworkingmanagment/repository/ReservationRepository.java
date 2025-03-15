package com.CoWorkSpace.v1.api.Coworkingmanagment.repository;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Reservation;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Reservation entities.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Retrieves all reservations made by a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of reservations associated with the given user ID.
     */
    List<Reservation> findByUserId(Long userId);

    /**
     * Finds a specific reservation made by a user on a given date.
     *
     * @param user The user who made the reservation.
     * @param date The date of the reservation.
     * @return An Optional containing the reservation if found, otherwise empty.
     */
    Optional<Reservation> findByUserAndDate(User user, LocalDate date);
}
