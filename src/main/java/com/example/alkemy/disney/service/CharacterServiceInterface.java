package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOImageName;

import java.util.List;
import java.util.Set;

public interface CharacterServiceInterface {

    CharacterDTO createCharacter(CharacterDTO characterDTO);
    CharacterDTO readCharacterById(Long id);
    CharacterDTO updateCharacter(CharacterDTO characterDTO);
    CharacterDTO deleteCharacterById(Long id);

    List<CharacterDTOImageName> readCharactersWithFilters(String name, Integer age, Integer weight, Set<Long> moviesSeries);
}
