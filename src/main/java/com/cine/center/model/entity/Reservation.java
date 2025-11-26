package com.cine.center.model.entity;

import com.cine.center.model.enums.ReserveStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotBlank(message = "Theater is mandatory")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    @Column(nullable = false)
    private String customerName;

    @NotNull(message = "Number of seats is mandatory")
    @Min(value = 1, message = "At least one seat must be reserved")
    @Max(value = 30, message = "Cannot reserve more than 30 seats")
    @Column(nullable = false)
    private Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ReserveStatus status;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
