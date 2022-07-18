package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.CharacterDTO;

public interface CharacterServiceInterface {

    CharacterDTO createCharacter(CharacterDTO characterDTO);
    CharacterDTO readCharacterById(Long id);
    CharacterDTO updateCharacter(CharacterDTO characterDTO);
    CharacterDTO deleteCharacterById(Long id);
}
