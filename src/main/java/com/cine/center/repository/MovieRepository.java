package com.cine.center.repository;

import com.cine.center.model.entity.Movie;
import com.cine.center.model.enums.MovieClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //find movie by title ignoring case
    Optional<Movie> findByTitleIgnoreCase(String title);

    //check if movie exists by title ignoring case
    boolean existsByTitleIgnoreCase(String title);

    //find movies by genre ignoring case
    List<Movie> findByGenreIgnoreCase(String genre);
    //find movies by classification
    List<Movie> findByClassification(MovieClassification classification);
}
