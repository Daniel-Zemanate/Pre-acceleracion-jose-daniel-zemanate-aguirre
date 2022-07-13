package com.example.alkemy.disney.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class MovieSeriesDTO {

    private Long idMovieSeries;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer grade;
//    private Set<CharacterDTO> characters;
//    private Set<CharacterEntity> characters = new HashSet<>();
    private GenreDTO genre;
    private Long genreId;
}
