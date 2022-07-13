package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOCreateUpdate;

public interface CharacterServiceInterface {

    CharacterDTO createCharacter(CharacterDTOCreateUpdate characterDTOCreateUpdate);
    CharacterDTO readCharacterById(Long id);
}
