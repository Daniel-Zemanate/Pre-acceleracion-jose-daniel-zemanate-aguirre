package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.CharacterDTO;

public interface CharacterServiceInterface {

    CharacterDTO findCharacter(Long id);
}
