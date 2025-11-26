package com.cine.center.service;

import com.cine.center.dto.movie.MovieRequestDTO;
import com.cine.center.dto.movie.MovieResponseDTO;

import java.util.List;

public interface MovieService {
    MovieResponseDTO create(MovieRequestDTO dto);
    List<MovieResponseDTO> getAll();
    MovieResponseDTO getById(Long id);
    MovieResponseDTO update(Long id, MovieRequestDTO dto);
    void delete(Long id);
}
