package com.cine.center.model.entity;

import com.cine.center.model.enums.MovieClassification;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title can have at most 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull(message = "Duration is mandatory")
    @Min(value = 30, message = "Duration must be at least 30 minutes")
    @Max(value = 900, message = "Duration must be at most 900 minutes")
    @Column(nullable = false)
    private Integer duration; //duration in minutes

    @NotBlank(message = "Genre is mandatory")
    @Size(max = 50, message = "Genre can have at most 50 characters")
    @Column(nullable = false, length = 50)
    private String genre;

    @NotNull(message = "Classification is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MovieClassification classification;

    // Relationships
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
