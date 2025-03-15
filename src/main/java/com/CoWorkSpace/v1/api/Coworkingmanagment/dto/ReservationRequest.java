package com.CoWorkSpace.v1.api.Coworkingmanagment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for reservation requests.
 * This class represents the request payload needed to create a new reservation.
 */
@Data
public class ReservationRequest {

    /**
     * The ID of the space being reserved.
     * Cannot be null.
     */
    @NotNull
    private Long spaceId;

    /**
     * The date of the reservation.
     * Cannot be null.
     */
    @NotNull
    private LocalDate date;

    /**
     * The start time of the reservation.
     * Cannot be null.
     */
    @NotNull
    private LocalTime startTime;
}
