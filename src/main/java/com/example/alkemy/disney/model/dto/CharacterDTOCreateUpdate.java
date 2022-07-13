package com.example.alkemy.disney.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTOCreateUpdate {
    private Long idCharacter;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
}
