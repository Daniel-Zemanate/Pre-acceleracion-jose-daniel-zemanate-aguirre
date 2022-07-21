package com.example.alkemy.disney.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieSeriesDTO {

    private Long idMovieSeries;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer grade;
    private Set<CharacterDTO> characters;
    private GenreDTO genre;
    private Long genreId;
}
