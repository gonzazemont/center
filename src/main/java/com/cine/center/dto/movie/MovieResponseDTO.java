package com.cine.center.dto.movie;

import com.cine.center.model.enums.MovieClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {

    private Long id;
    private String title;
    private Integer duration; // duration in minutes
    private String genre;
    private MovieClassification classification;
}
