package com.cine.center.dto.reservation;

import com.cine.center.model.enums.ReserveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private String dateTime;
    private String customerName;
    private Integer numberOfSeats;
    private ReserveStatus status;
    private Long theaterId;
    private Long movieId;
}
