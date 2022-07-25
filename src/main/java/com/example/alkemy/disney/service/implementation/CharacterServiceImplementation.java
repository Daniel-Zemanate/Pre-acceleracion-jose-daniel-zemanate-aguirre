package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.exception.MyEntityIdControlException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOImageName;
import com.example.alkemy.disney.model.dto.CharacterFiltersDTO;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.mapper.CharacterMapper;
import com.example.alkemy.disney.repository.CharacterRepository;
import com.example.alkemy.disney.repository.specification.CharacterSpecification;
import com.example.alkemy.disney.service.CharacterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImplementation implements CharacterServiceInterface {

    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;
    private CharacterSpecification characterSpecification;

    @Autowired
    public CharacterServiceImplementation(CharacterRepository characterRepository, CharacterMapper characterMapper, CharacterSpecification characterSpecification) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.characterSpecification = characterSpecification;
    }

    @Override
    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        //VALIDATION: AUTOINCREMENT ID. NO NEEDED.
        if (characterDTO.getIdCharacter() != null){

            throw new MyEntityIdControlException("Trying to create a character with id= "+ characterDTO.getIdCharacter() +" included");
        }

        CharacterDTO responseCharacterDTO = characterMapper.toCharacterDTO(characterRepository.save(characterMapper.toCharacterEntity(characterDTO)), true);

        return responseCharacterDTO;
    }

    @Override
    public CharacterDTO readCharacterById(Long id) {
        Optional<CharacterEntity> entityCheck = characterRepository.findById(id);
        CharacterDTO responseCharacterDTO = new CharacterDTO();

        if (!entityCheck.isPresent()){

            throw new MyNotFoundIdException("can not find any character with id= "+ id);
        }

        responseCharacterDTO = characterMapper.toCharacterDTO(entityCheck.get(),true);

        return responseCharacterDTO;
    }

    @Override
    public CharacterDTO updateCharacter(CharacterDTO characterDTO) {
        if (characterDTO.getIdCharacter() == null){

            throw new MyEntityIdControlException("Trying to update a character with id= "+ characterDTO.getIdCharacter());
        }

        //VALIDATION: ID EXISTS.
        readCharacterById(characterDTO.getIdCharacter());

        return characterMapper.toCharacterDTO(characterRepository.save(characterMapper.toCharacterEntity(characterDTO)), true);
    }

    @Override
    public CharacterDTO deleteCharacterById(Long id) {
        //ID VALIDATION
        CharacterDTO responseCharacterDTO = readCharacterById(id);

        characterRepository.deleteById(id);

        return responseCharacterDTO;
    }

    @Override
    public List<CharacterDTOImageName> readCharactersWithFilters(String name, Integer age, Integer weight,  Set<Long> moviesSeries) {
        CharacterFiltersDTO characterFilters = new CharacterFiltersDTO(name, age, weight, moviesSeries);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.readByFilters(characterFilters));

        return characterMapper.toListCharacterDTOImageName(entities);
    }
}
