package com.cine.center.service.impl;

import com.cine.center.dto.movie.MovieRequestDTO;
import com.cine.center.dto.movie.MovieResponseDTO;
import com.cine.center.exception.DuplicateResourceException;
import com.cine.center.exception.ResourceNotFoundException;
import com.cine.center.mapper.MovieMapper;
import com.cine.center.model.entity.Movie;
import com.cine.center.repository.MovieRepository;
import com.cine.center.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponseDTO create(MovieRequestDTO dto) {
        if (movieRepository.existsByTitleIgnoreCase(dto.getTitle())){
            throw new DuplicateResourceException("Movie with title " + dto.getTitle() + " already exists");
        }

        Movie movie = movieMapper.toEntity(dto);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDTO(savedMovie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponseDTO> getAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponseDTO getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id " + id + " not found"));
        return movieMapper.toDTO(movie);
    }

    @Override
    public MovieResponseDTO update(Long id, MovieRequestDTO dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id " + id + " not found"));

        if(movieRepository.existsByTitleIgnoreCase(dto.getTitle())
                && !movie.getTitle().equalsIgnoreCase(dto.getTitle())){
            throw new DuplicateResourceException("Movie with title " + dto.getTitle() + " already exists");
        }

        movieMapper.toEntity(movie, dto);
        Movie updatedMovie = movieRepository.save(movie);
        return movieMapper.toDTO(updatedMovie);
    }

    @Override
    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id " + id + " not found"));
        movieRepository.deleteById(id);
    }
}
