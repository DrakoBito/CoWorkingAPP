package com.CoWorkSpace.v1.api.Coworkingmanagment.service;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Reservation;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Space;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.ReservationRepository;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SpaceRepository spaceRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_ShouldSaveReservation_WhenSpaceExists() {
        //test data
        User user = new User("Juan", "juan@example.com", "hashedPassword");
        Space space = new Space(1L, "Sala de reuniones", "Con proyector");
        LocalDate date = LocalDate.of(2025, 3, 15);
        LocalTime startTime = LocalTime.of(8, 0);

        //simulating repository
        when(spaceRepository.findById(1L)).thenReturn(Optional.of(space));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //call the method to test
        Reservation reservation = reservationService.createReservation(user, 1L, date, startTime);

        //verifications
        assertNotNull(reservation);
        assertEquals(user, reservation.getUser());
        assertEquals(space, reservation.getSpace());
        assertEquals(date, reservation.getDate());
        assertEquals(startTime, reservation.getStartTime());

        //verify that the repository save method was called
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void createReservation_ShouldThrowException_WhenSpaceDoesNotExist() {
        //data for the test
        User user = new User("Juan", "juan@example.com", "hashedPassword");

        when(spaceRepository.findById(1L)).thenReturn(Optional.empty());

        //exception verification
        assertThrows(IllegalStateException.class, () -> reservationService.createReservation(user, 1L, LocalDate.now(), LocalTime.now()));
    }
}
