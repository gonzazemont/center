package com.cine.center.service.impl;

import com.cine.center.dto.reservation.ReservationRequestDTO;
import com.cine.center.dto.reservation.ReservationResponseDTO;
import com.cine.center.dto.reservation.ReservationUpdateStatusDTO;
import com.cine.center.exception.BusinessException;
import com.cine.center.exception.ResourceNotFoundException;
import com.cine.center.mapper.ReservationMapper;
import com.cine.center.model.entity.Movie;
import com.cine.center.model.entity.Reservation;
import com.cine.center.model.entity.Theater;
import com.cine.center.model.enums.ReserveStatus;
import com.cine.center.repository.MovieRepository;
import com.cine.center.repository.ReservationRepository;
import com.cine.center.repository.TheaterRepository;
import com.cine.center.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    @Override
    public ReservationResponseDTO create(ReservationRequestDTO dto) {
        if(dto.getDateTime().isBefore(LocalDateTime.now())){
            throw new BusinessException("Reservation date and time cannot be in the past");
        }

        Theater theater = theaterRepository.findById(dto.getTheaterId())
                .orElseThrow(()-> new ResourceNotFoundException("Theater with id " + dto.getTheaterId() + " not found"));

        
        int reservedSeats = reservationRepository.sumReservedSeatsByTheaterAndDateTime(dto.getTheaterId(), dto.getDateTime());
        if(reservedSeats + dto.getNumberOfSeats() > theater.getCapacity()){
            throw new BusinessException("Not enough available seats for the selected theater at the requested time");
        }

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id " + dto.getMovieId() + " not found"));



        Reservation reservation = reservationMapper.toEntity(dto);
        reservation.setTheater(theater);
        reservation.setMovie(movie);
        reservation.setStatus(ReserveStatus.PENDING);

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getAll() {
        return reservationRepository.findAllWithRelations()
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDTO getById(Long id) {
        Reservation reservation = reservationRepository.findByIdWithRelations(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation with id " + id + " not found"));
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public ReservationResponseDTO update(Long id, ReservationRequestDTO dto) {
        Reservation reservation = reservationRepository.findByIdWithRelations(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation with id " + id + " not found"));


        reservationMapper.updateEntityFromDTO(reservation, dto);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(updatedReservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation with id " + id + " not found"));
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationResponseDTO updateStatus(Long id, ReservationUpdateStatusDTO dto) {
        Reservation reservation = reservationRepository.findByIdWithRelations(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation with id " + id + " not found"));

        reservation.setStatus(dto.getStatus());
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(updatedReservation);
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByDate(LocalDate date) {
        return reservationRepository.findByDateWithRelations(date)
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }
}
