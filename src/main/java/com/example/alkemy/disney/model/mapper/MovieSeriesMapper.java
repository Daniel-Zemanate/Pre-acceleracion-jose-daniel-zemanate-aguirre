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

    public MovieSeriesEntity toMovieSeriesEntity(MovieSeriesDTO dto, Boolean includeCharacters){
//    public MovieSeriesEntity toMovieSeriesEntity(MovieSeriesDTO dto, Boolean includeCharacters, Boolean includeGenre){
        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();

        movieSeriesEntity.setIdMovieSeries(dto.getIdMovieSeries());
        movieSeriesEntity.setImage(dto.getImage());
        movieSeriesEntity.setTitle(dto.getTitle());
        movieSeriesEntity.setCreationDate(dto.getCreationDate());
        movieSeriesEntity.setGrade(dto.getGrade());
        if (includeCharacters){
            movieSeriesEntity.setCharacters(characterMapper.toSetListCharacterEntity(dto.getCharacters()));
        }
//        if (includeGenre){
//            movieSeriesEntity.setGenre(genreMapper.toGenreEntity(dto.getGenre()));
//        }

        movieSeriesEntity.setGenreId(dto.getGenre().getIdGenre());

        return movieSeriesEntity;
    }
//    public MovieSeriesEntity toMovieSeriesEntity(MovieSeriesDTOCreateUpdate dto, Boolean includeCharacters){
//        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();
//
//        movieSeriesEntity.setIdMovieSeries(dto.getIdMovieSeries());
//        movieSeriesEntity.setImage(dto.getImage());
//        movieSeriesEntity.setTitle(dto.getTitle());
//        movieSeriesEntity.setCreationDate(dto.getCreationDate());
//        movieSeriesEntity.setGrade(dto.getGrade());
//
//        if (includeCharacters){
//
//            movieSeriesEntity.setCharacters(characterMapper.toSetListCharacterEntity(dto.getCharacters()));
//        }
//
//        movieSeriesEntity.setGenreId(dto.getGenreId());
//
//        return movieSeriesEntity;
//    }

    public List<MovieSeriesEntity> toListMovieSeriesEntity(List<MovieSeriesDTO> dtoList){
        List<MovieSeriesEntity> movieSeriesEntityList = new ArrayList<>();

        return movieSeriesEntityList;
    }

    //-------------- FROM DTO TO ENTITY --------------

    //-------------- FROM ENTITY TO DTO --------------
    public MovieSeriesDTO toMovieSeriesDTO(MovieSeriesEntity entity, Boolean loadDetails){
        MovieSeriesDTO movieSeriesDTO = new MovieSeriesDTO();

        movieSeriesDTO.setIdMovieSeries(entity.getIdMovieSeries());
        movieSeriesDTO.setImage(entity.getImage());
        movieSeriesDTO.setTitle(entity.getTitle());
        movieSeriesDTO.setCreationDate(entity.getCreationDate());
        movieSeriesDTO.setGrade(entity.getGrade());

        if (loadDetails){
            movieSeriesDTO.setCharacters(characterMapper.toSetListCharacterDTO(entity.getCharacters()));
            movieSeriesDTO.setGenre(genreMapper.toGenreDTO(entity.getGenre()));
        }


        return movieSeriesDTO;
    }

    public List<MovieSeriesDTO> toListMovieSeriesDTO(List<MovieSeriesEntity> entityList){
        List<MovieSeriesDTO> movieSeriesDTOList = new ArrayList<>();

        if (entityList != null){
            for (MovieSeriesEntity movieSeriesEntity : entityList) {

                movieSeriesDTOList.add(toMovieSeriesDTO(movieSeriesEntity, false));
            }
        }

        return movieSeriesDTOList;
    }


    public List<MovieSeriesDTOImageTitleDate> toListMovieSeriesDTOImageTitleDate(List<MovieSeriesEntity> entityList){
        List<MovieSeriesDTOImageTitleDate> movieSeriesDTOList = new ArrayList<>();

        return movieSeriesDTOList;
    }


    //-------------- FROM ENTITY TO DTO --------------
}
