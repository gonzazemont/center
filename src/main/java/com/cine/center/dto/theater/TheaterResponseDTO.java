package com.cine.center.dto.theater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponseDTO {
    private Long id;
    private String name;
    private Integer capacity;
}
