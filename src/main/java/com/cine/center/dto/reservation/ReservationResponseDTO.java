package com.cine.center.dto.reservation;

import com.cine.center.model.enums.ReserveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String customerName;
    private Integer numberOfSeats;
    private ReserveStatus status;

    private Long theaterId;
    private String theaterName;

    private Long movieId;
    private String movieTitle;
}
