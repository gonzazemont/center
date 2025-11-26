package com.cine.center.service;

import com.cine.center.dto.reservation.ReservationRequestDTO;
import com.cine.center.dto.reservation.ReservationResponseDTO;
import com.cine.center.dto.reservation.ReservationUpdateStatusDTO;
import com.cine.center.model.enums.ReserveStatus;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDTO create(ReservationRequestDTO dto);
    List<ReservationResponseDTO> getAll();
    ReservationResponseDTO getById(Long id);
    ReservationResponseDTO update(Long id, ReservationRequestDTO dto);
    void delete(Long id);

    ReservationResponseDTO updateStatus(Long id, ReservationUpdateStatusDTO dto);

    // get reservations by date
    List<ReservationResponseDTO> getReservationsByDate(LocalDate date);
}
