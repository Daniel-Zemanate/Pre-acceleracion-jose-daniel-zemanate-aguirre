package com.example.alkemy.disney.model.mapper;

import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.model.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
    //-------------- FROM DTO TO ENTITY --------------
    public GenreEntity toGenreEntity(GenreDTO dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(dto.getName());
        genreEntity.setImage(dto.getImage());
        return genreEntity;
    }

    public List<GenreEntity> toListGenreEntity(List<GenreDTO> dtoList){
        List<GenreEntity> genreEntityList = new ArrayList<>();
        for (GenreDTO genreDTO : dtoList) {
            genreEntityList.add(toGenreEntity(genreDTO));
        }
        return genreEntityList;
    }
    //-------------- FROM DTO TO ENTITY --------------

    //-------------- FROM ENTITY TO DTO --------------
    public GenreDTO toGenreDTO(GenreEntity entity){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setIdGenre(entity.getIdGenre());
        genreDTO.setName(entity.getName());
        genreDTO.setImage(entity.getImage());
        return genreDTO;
    }

    public List<GenreDTO> toListGenreDTO(List<GenreEntity> entityList){
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (GenreEntity genreEntity : entityList) {
            genreDTOList.add(toGenreDTO(genreEntity));
        }
        return genreDTOList;
    }
    //-------------- FROM ENTITY TO DTO --------------

}
