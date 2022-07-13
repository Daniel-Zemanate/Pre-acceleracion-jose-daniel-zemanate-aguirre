package com.example.alkemy.disney.model.mapper;

import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.model.dto.MovieSeriesDTOImageTitleDate;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//    private Long idMovieSeries;
//    private String image;
//    private String title;
//    private LocalDate creationDate;
//    private Integer grade;
////    private Set<CharacterDTO> characters;
////    private Set<CharacterEntity> characters = new HashSet<>();
////    private GenreDTO genre;
////    private Long genreId;

//TODO: COMPLETAR EL COMPORTAMIENTO DEL MAPEO PARA PELICULAS/SERIES SEGUN EL REQUERIMIENTO
@Component
public class MovieSeriesMapper {
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterMapper characterMapper;
//
//    @Autowired
//    public MovieSeriesMapper(GenreMapper genreMapper, CharacterMapper characterMapper) {
//        this.genreMapper = genreMapper;
//        this.characterMapper = characterMapper;
//    }
    //-------------- FROM DTO TO ENTITY --------------

    public MovieSeriesEntity toMovieSeriesEntity(MovieSeriesDTO dto){
        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();
        movieSeriesEntity.setImage(dto.getImage());
        movieSeriesEntity.setTitle(dto.getTitle());
        movieSeriesEntity.setCreationDate(dto.getCreationDate());
        movieSeriesEntity.setGrade(dto.getGrade());
        movieSeriesEntity.setGenreId(dto.getGenreId());

        return movieSeriesEntity;
    }

    public List<MovieSeriesEntity> toListMovieSeriesEntity(List<MovieSeriesDTO> dtoList){
        List<MovieSeriesEntity> movieSeriesEntityList = new ArrayList<>();

        for (MovieSeriesDTO movieSeriesDTO : dtoList) {

            movieSeriesEntityList.add(toMovieSeriesEntity(movieSeriesDTO));
        }

        return movieSeriesEntityList;
    }

    //-------------- FROM DTO TO ENTITY --------------

    //-------------- FROM ENTITY TO DTO --------------
    public MovieSeriesDTO toMovieSeriesDTO(MovieSeriesEntity entity, Boolean loadCharacterList){
        MovieSeriesDTO movieSeriesDTO = new MovieSeriesDTO();
        movieSeriesDTO.setGenreId(entity.getGenreId());
        movieSeriesDTO.setImage(entity.getImage());
        movieSeriesDTO.setTitle(entity.getTitle());
        movieSeriesDTO.setCreationDate(entity.getCreationDate());
        movieSeriesDTO.setGrade(entity.getGrade());
        movieSeriesDTO.setGenre(genreMapper.toGenreDTO(entity.getGenre()));

        if (loadCharacterList){

        }

        return movieSeriesDTO;
    }

    public List<MovieSeriesDTOImageTitleDate> toListMovieSeriesDTO(List<MovieSeriesEntity> entityList){
        List<MovieSeriesDTOImageTitleDate> movieSeriesDTOList = new ArrayList<>();
        MovieSeriesDTOImageTitleDate movieSeriesDTO = new MovieSeriesDTOImageTitleDate();

        for (MovieSeriesEntity movieSeriesEntity : entityList) {
            movieSeriesDTO.setImage(movieSeriesEntity.getImage());
            movieSeriesDTO.setTitle(movieSeriesEntity.getTitle());
            movieSeriesDTO.setCreationDate(movieSeriesEntity.getCreationDate());

            movieSeriesDTOList.add(movieSeriesDTO);
        }

        return movieSeriesDTOList;
    }


    //-------------- FROM ENTITY TO DTO --------------
}
