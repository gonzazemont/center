package com.cine.center.controller;

import com.cine.center.dto.reservation.ReservationRequestDTO;
import com.cine.center.dto.reservation.ReservationResponseDTO;
import com.cine.center.dto.reservation.ReservationUpdateStatusDTO;
import com.cine.center.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService service;

    //create reservation
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@Valid @RequestBody ReservationRequestDTO dto){
        ReservationResponseDTO createdReservation = service.create(dto);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    //get all reservations
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAll(){
        List<ReservationResponseDTO> reservations = service.getAll();
        return ResponseEntity.ok(reservations);
    }

    //get reservation by id
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getById(@PathVariable Long id) {
        ReservationResponseDTO reservation = service.getById(id);
        return ResponseEntity.ok(reservation);
    }

    //update status of reservation
    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationResponseDTO> updateStatus(@PathVariable Long id, @RequestBody ReservationUpdateStatusDTO dto) {
        ReservationResponseDTO updatedReservation = service.updateStatus(id, dto);
        return ResponseEntity.ok(updatedReservation);
    }

    //find reservations by date
    @GetMapping("/by-date")
    public ResponseEntity<List<ReservationResponseDTO>> getByDate(@RequestParam("date") LocalDate date) {
        List<ReservationResponseDTO> reservations = service.getReservationsByDate(date);
        return ResponseEntity.ok(reservations);
    }
}
