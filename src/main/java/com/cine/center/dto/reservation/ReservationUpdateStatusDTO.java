package com.cine.center.dto.reservation;

import com.cine.center.model.enums.ReserveStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUpdateStatusDTO {

    @NotNull(message = "Status is mandatory")
    private ReserveStatus status;
}
