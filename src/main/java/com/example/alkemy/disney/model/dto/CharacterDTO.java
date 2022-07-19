package com.example.alkemy.disney.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private Long idCharacter;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
    private List<MovieSeriesDTO> movieSeriesList;

}
