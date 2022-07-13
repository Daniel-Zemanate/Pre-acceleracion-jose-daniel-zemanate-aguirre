package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.exception.MyCreationWithIdException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOCreateUpdate;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.mapper.CharacterMapper;
import com.example.alkemy.disney.repository.CharacterRepository;
import com.example.alkemy.disney.service.CharacterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImplementation implements CharacterServiceInterface {

    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImplementation(CharacterRepository characterRepository, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public CharacterDTO createCharacter(CharacterDTOCreateUpdate characterDTOCreateUpdate) {
        //VALIDATION: AUTOINCREMENT ID. NO NEEDED.
        if (characterDTOCreateUpdate.getIdCharacter() != null){
            throw new MyCreationWithIdException("Something went wrong when createCharacter in -CharacterServiceImplementation-");
        }

        CharacterDTO responseCharacterDTO = characterMapper.toCharacterDTO(characterRepository.save(characterMapper.toCharacterEntity(characterDTOCreateUpdate)), false);

        return responseCharacterDTO;
    }

    @Override
    public CharacterDTO readCharacterById(Long id) {
        Optional<CharacterEntity> entityCheck = characterRepository.findById(id);
        CharacterDTO responseCharacterDTO = new CharacterDTO();

        if (!entityCheck.isPresent()){
            throw new MyNotFoundIdException("Something went wrong when findCharacterById: "+id+" in -CharacterServiceImplementation-");
        }

        responseCharacterDTO = characterMapper.toCharacterDTO(entityCheck.get(),false);

        return responseCharacterDTO;
    }
}
