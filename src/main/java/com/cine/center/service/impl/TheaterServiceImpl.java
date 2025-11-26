package com.cine.center.service.impl;

import com.cine.center.dto.theater.TheaterRequestDTO;
import com.cine.center.dto.theater.TheaterResponseDTO;
import com.cine.center.exception.DuplicateResourceException;
import com.cine.center.exception.ResourceNotFoundException;
import com.cine.center.mapper.TheaterMapper;
import com.cine.center.model.entity.Theater;
import com.cine.center.repository.TheaterRepository;
import com.cine.center.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;

    @Override
    public TheaterResponseDTO create(TheaterRequestDTO dto) {
        if(theaterRepository.existsByName(dto.getName())){
            throw new DuplicateResourceException("Theater with name " + dto.getName() + " already exists");
        }

        Theater theater = theaterMapper.toEntity(dto);
        Theater savedTheater = theaterRepository.save(theater);
        return theaterMapper.toDTO(savedTheater);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TheaterResponseDTO> getAll() {
        return theaterRepository.findAll()
                .stream()
                .map(theaterMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TheaterResponseDTO getById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater with id " + id + " not found"));
        return theaterMapper.toDTO(theater);
    }

    @Override
    public TheaterResponseDTO update(Long id, TheaterRequestDTO dto) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater with id " + id + " not found"));

        if(theaterRepository.existsByName(dto.getName())
                && !theater.getName().equalsIgnoreCase(dto.getName())){
            throw new DuplicateResourceException("Theater with name " + dto.getName() + " already exists");
        }

        theaterMapper.updateEntityFromDTO(theater, dto);
        Theater updatedTheater = theaterRepository.save(theater);
        return theaterMapper.toDTO(updatedTheater);
    }

    @Override
    public void delete(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater with id " + id + " not found"));
        theaterRepository.deleteById(id);
    }

}
