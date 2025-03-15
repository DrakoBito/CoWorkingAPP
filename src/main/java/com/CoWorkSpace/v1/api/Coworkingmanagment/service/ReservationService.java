package com.CoWorkSpace.v1.api.Coworkingmanagment.service;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Reservation;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Space;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.ReservationRepository;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SpaceRepository spaceRepository;

    /**
     * Retrieves all reservations for a given user.
     *
     * @param userId The user's ID.
     * @return A list of reservations.
     */
    public List<Reservation> getReservationForUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    /**
     * Creates a new reservation for a user.
     *
     * @param user      The user making the reservation.
     * @param spaceId   The ID of the space being reserved.
     * @param date      The date of the reservation.
     * @param startTime The start time of the reservation.
     * @return The saved reservation.
     * @throws IllegalStateException If the date is in the past or the user already has a reservation for the given date.
     */
    public Reservation createReservation(User user, Long spaceId, LocalDate date, LocalTime startTime) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalStateException("The reservation date cannot be in the past.");
        }

        reservationRepository.findByUserAndDate(user, date).ifPresent(reservation -> {
            throw new IllegalStateException("The user already has a reservation for this date.");
        });

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new IllegalStateException("The requested space was not found."));

        Reservation reservation = new Reservation(user, space, date, startTime);
        return reservationRepository.save(reservation);
    }

    /**
     * Deletes a reservation if the user has permission and meets the required time restrictions.
     *
     * @param user         The user requesting the deletion.
     * @param reservationId The ID of the reservation to delete.
     * @throws IllegalStateException If the reservation does not exist, the user is not the owner, or the deletion
     *                               is attempted within one hour of the reservation start time.
     */
    public void deleteReservation(User user, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("The requested reservation was not found."));

        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("The user does not have permission to delete this reservation.");
        }

        if (reservation.getDate().isEqual(LocalDate.now())) {
            long minutesDifference = ChronoUnit.MINUTES.between(LocalTime.now(), reservation.getStartTime());
            if (minutesDifference < 60) {
                throw new IllegalStateException("Reservations cannot be deleted less than 1 hour before the start time.");
            }
        }

        reservationRepository.delete(reservation);
    }
}
