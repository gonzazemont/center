package com.cine.center.dto.movie;

import com.cine.center.model.enums.MovieClassification;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDTO {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title can have at most 100 characters")
    private String title;

    @NotNull(message = "Duration is mandatory")
    @Min(value = 30, message = "Duration must be at least 30 minutes")
    @Max(value = 900, message = "Duration must be at most 900 minutes")
    private Integer duration; // duration in minutes

    @NotBlank(message = "Genre is mandatory")
    @Size(max = 50, message = "Genre can have at most 50 characters")
    private String genre;

    @NotNull(message = "Classification is mandatory")
    private MovieClassification classification;
}
