package com.CoWorkSpace.v1.api.Coworkingmanagment.controller;

import com.CoWorkSpace.v1.api.Coworkingmanagment.dto.ReservationRequest;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Reservation;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import com.CoWorkSpace.v1.api.Coworkingmanagment.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling reservation-related operations.
 * - Allows users to create, retrieve, and delete reservations.
 */
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * Retrieves all reservations for the authenticated user.
     *
     * @param user The authenticated user.
     * @return A list of reservations associated with the user.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getUserReservations(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(reservationService.getReservationForUser(user.getId()));
    }

    /**
     * Creates a new reservation.
     *
     * @param user The authenticated user making the reservation.
     * @param request The reservation request containing space ID, date, and start time.
     * @return The created reservation.
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@AuthenticationPrincipal User user,
                                                         @Valid @RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.createReservation(user, request.getSpaceId(), request.getDate(), request.getStartTime());
        return ResponseEntity.ok(reservation);
    }

    /**
     * Cancels a reservation by ID.
     *
     * @param user The authenticated user who owns the reservation.
     * @param id The ID of the reservation to be deleted.
     * @return A confirmation message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@AuthenticationPrincipal User user, @PathVariable Long id) {
        reservationService.deleteReservation(user, id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }
}
