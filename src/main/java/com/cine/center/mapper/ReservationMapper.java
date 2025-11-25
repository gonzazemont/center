package com.cine.center.mapper;

import com.cine.center.dto.reservation.ReservationRequestDTO;
import com.cine.center.dto.reservation.ReservationResponseDTO;
import com.cine.center.model.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    //to entity(create)
    public Reservation toEntity(ReservationRequestDTO dto){
        Reservation reservation = new Reservation();
        reservation.setDateTime(dto.getDateTime());
        reservation.setCustomerName(dto.getCustomerName());
        reservation.setNumberOfSeats(dto.getNumberOfSeats());
        // Note: status, theater, and movie should be set in the service layer
        return reservation;
    }
    //update entity
    public void updateEntityFromDTO(Reservation reservation, ReservationRequestDTO dto){
        reservation.setDateTime(dto.getDateTime());
        reservation.setCustomerName(dto.getCustomerName());
        reservation.setNumberOfSeats(dto.getNumberOfSeats());
        // Note: status, theater, and movie should be updated in the service layer if needed
    }
    //to dto
    public ReservationResponseDTO toDTO(Reservation reservation){
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getId());
        dto.setDateTime(reservation.getDateTime());
        dto.setCustomerName(reservation.getCustomerName());
        dto.setNumberOfSeats(reservation.getNumberOfSeats());
        dto.setStatus(reservation.getStatus());

        if(reservation.getTheater() != null) {
            dto.setTheaterId(reservation.getTheater().getId());
            dto.setTheaterName(reservation.getTheater().getName());
        }

        if (reservation.getMovie() != null) {
            dto.setMovieId(reservation.getMovie().getId());
            dto.setMovieTitle(reservation.getMovie().getTitle());
        }

        return dto;
    }
}
