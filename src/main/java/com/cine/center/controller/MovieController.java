package com.cine.center.controller;

import com.cine.center.dto.movie.MovieRequestDTO;
import com.cine.center.dto.movie.MovieResponseDTO;
import com.cine.center.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    //get all movies
    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<MovieResponseDTO> movies = service.getAll();
        return ResponseEntity.ok(movies);
    }

    //get movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(Long id) {
        MovieResponseDTO movie = service.getById(id);
        return ResponseEntity.ok(movie);
    }

    //create movie
    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@Valid @RequestBody MovieRequestDTO dto){
        MovieResponseDTO createdMovie = service.create(dto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    //update movie
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MovieRequestDTO dto){
        MovieResponseDTO updatedMovie = service.update(id, dto);
        return ResponseEntity.ok(updatedMovie);
    }

    //delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
