package com.cine.center.mapper;

import com.cine.center.dto.movie.MovieRequestDTO;
import com.cine.center.dto.movie.MovieResponseDTO;
import com.cine.center.model.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    //to entity (create)
    public Movie toEntity(MovieRequestDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDuration(dto.getDuration());
        movie.setGenre(dto.getGenre());
        movie.setClassification(dto.getClassification());
        return movie;
    }

    //to entity (update)
    public void toEntity(Movie movie, MovieRequestDTO dto) {
        movie.setTitle(dto.getTitle());
        movie.setDuration(dto.getDuration());
        movie.setGenre(dto.getGenre());
        movie.setClassification(dto.getClassification());
    }

    //to dto
    public MovieResponseDTO toDTO(Movie movie) {
        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDuration(movie.getDuration());
        dto.setGenre(movie.getGenre());
        dto.setClassification(movie.getClassification());
        return dto;
    }


}
