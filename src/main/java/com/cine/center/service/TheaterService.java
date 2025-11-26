package com.cine.center.service;

import com.cine.center.dto.theater.TheaterRequestDTO;
import com.cine.center.dto.theater.TheaterResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface TheaterService {

    TheaterResponseDTO create(TheaterRequestDTO dto);
    List<TheaterResponseDTO> getAll();
    TheaterResponseDTO getById(Long id);
    TheaterResponseDTO update(Long id, TheaterRequestDTO dto);
    void delete(Long id);


}
