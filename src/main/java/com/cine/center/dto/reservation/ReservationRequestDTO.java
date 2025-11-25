package com.cine.center.dto.reservation;

import com.cine.center.model.enums.ReserveStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {

    @NotNull(message = "Date and time is mandatory")
    @Future(message = "Reservation date and time must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Customer name is mandatory")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    private String customerName;

    @NotNull(message = "Number of seats is mandatory")
    @Min(value = 1, message = "At least one seat must be reserved")
    @Max(value = 30, message = "Cannot reserve more than 30 seats")
    private Integer numberOfSeats;

    @NotNull(message = "Theater ID is mandatory")
    private Long theaterId;

    @NotNull(message = "Movie ID is mandatory")
    private Long movieId;
}
