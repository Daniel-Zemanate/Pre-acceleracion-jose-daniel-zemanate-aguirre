package com.example.alkemy.disney.model.mapper;

import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOImageName;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class CharacterMapper {

    @Autowired
    private MovieSeriesMapper movieSeriesMapper;


    //-------------- FROM DTO TO ENTITY --------------

    public CharacterEntity toCharacterEntity(CharacterDTO dto){
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setIdCharacter(dto.getIdCharacter());
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setStory(dto.getStory());

        return characterEntity;
    }

    public Set<CharacterEntity> toSetListCharacterEntity(Set<CharacterDTO> dtoList){
        Set<CharacterEntity> characterEntitySetList = new HashSet<>();

        if (dtoList != null){
            for (CharacterDTO characterDTO : dtoList) {

                characterEntitySetList.add(toCharacterEntity(characterDTO));
            }
        }

        return characterEntitySetList;
    }
    //-------------- FROM DTO TO ENTITY --------------

    //-------------- FROM ENTITY TO DTO --------------
    public CharacterDTO toCharacterDTO(CharacterEntity entity, Boolean loadMovieSeriesList){
        CharacterDTO characterDTO = new CharacterDTO();

        characterDTO.setIdCharacter(entity.getIdCharacter());
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setStory(entity.getStory());
        if (loadMovieSeriesList){

            characterDTO.setMovieSeriesList(movieSeriesMapper.toListMovieSeriesDTO(entity.getMovieSeriesList()));
        }

        return characterDTO;
    }


    public Set<CharacterDTO> toSetListCharacterDTO(Set<CharacterEntity> entityList){
        Set<CharacterDTO> characterDTOSetList = new HashSet<>();

        if (entityList != null){
            for (CharacterEntity characterEntity: entityList) {

                characterDTOSetList.add(toCharacterDTO(characterEntity,false));
            }
        }

        return characterDTOSetList;
    }


    public List<CharacterDTOImageName> toListCharacterDTOImageName(List<CharacterEntity> entityList){
        List<CharacterDTOImageName> characterDTOImageNameList = new ArrayList<>();

        for (CharacterEntity characterEntity: entityList) {
            CharacterDTOImageName characterDTOImageName = new CharacterDTOImageName();
            characterDTOImageName.setImage(characterEntity.getImage());
            characterDTOImageName.setName(characterEntity.getName());

            characterDTOImageNameList.add(characterDTOImageName);
        }

        return characterDTOImageNameList;
    }

    //-------------- FROM ENTITY TO DTO --------------


}
