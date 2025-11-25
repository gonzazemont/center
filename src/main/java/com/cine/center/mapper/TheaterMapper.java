package com.cine.center.mapper;

import com.cine.center.dto.theater.TheaterRequestDTO;
import com.cine.center.dto.theater.TheaterResponseDTO;
import com.cine.center.model.entity.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {

    //to entity(create)
    public Theater toEntity(TheaterRequestDTO dto) {
        Theater theater = new Theater();
        theater.setName(dto.getName());
        theater.setCapacity(dto.getCapacity());
        return theater;
    }

    //update entity
    public void updateEntityFromDTO(Theater theater, TheaterRequestDTO dto) {
        theater.setName(dto.getName());
        theater.setCapacity(dto.getCapacity());
    }

    //to dto
    public TheaterResponseDTO toDTO(Theater theater) {
        TheaterResponseDTO dto = new TheaterResponseDTO();
        dto.setId(theater.getId());
        dto.setName(theater.getName());
        dto.setCapacity(theater.getCapacity());
        return dto;
    }


}
