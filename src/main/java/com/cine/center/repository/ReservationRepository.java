package com.cine.center.repository;

import com.cine.center.model.entity.Reservation;
import com.cine.center.model.enums.ReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //find reservations by customer name
    List<Reservation> findByCustomerName(String customerName);

    //find reservations by theater id
    List<Reservation> findByTheaterId(Long theaterId);

    //find reservations by movie id
    List<Reservation> findByMovieId(Long movieId);

    //find reservations by status
    List<Reservation> findByStatus(ReserveStatus status);

    //summarize total reserved seats for a specific theater and dateTime excluding cancelled reservations
    @Query("SELECT COALESCE(SUM(r.numberOfSeats), 0) FROM Reservation r " +
            "WHERE r.theater.id = :theaterId AND r.dateTime = :dateTime " +
            "AND r.status != com.cine.center.model.enums.ReserveStatus.CANCELLED")
    Integer sumReservedSeatsByTheaterAndDateTime(
            @Param("theaterId") Long theaterId,
            @Param("dateTime") LocalDateTime dateTime
    );

    //check if a reservation exists by customer name
    boolean existsByCustomerName(String customerName);

    //find reservations within a date range
    List<Reservation> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    //for sorted results
    List<Reservation> findByTheaterIdOrderByDateTimeAsc(Long theaterId);

    //for finding reservations on a specific date
    @Query("SELECT r FROM Reservation r WHERE DATE(r.dateTime) = :date")
    List<Reservation> findByDate(@Param("date") LocalDate date);

}
