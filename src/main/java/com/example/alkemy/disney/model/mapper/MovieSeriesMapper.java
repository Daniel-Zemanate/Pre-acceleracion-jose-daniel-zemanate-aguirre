package com.example.alkemy.disney.model.mapper;

import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.stereotype.Component;


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
    //-------------- FROM DTO TO ENTITY --------------

    public MovieSeriesEntity toMovieSeriesEntity(MovieSeriesDTO dto){
        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();
        movieSeriesEntity.setImage(dto.getImage());
        movieSeriesEntity.setTitle(dto.getTitle());
        movieSeriesEntity.setCreationDate(dto.getCreationDate());
        movieSeriesEntity.setGrade(dto.getGrade());

        return movieSeriesEntity;
    }

    //-------------- FROM DTO TO ENTITY --------------

    //-------------- FROM ENTITY TO DTO --------------


    //-------------- FROM ENTITY TO DTO --------------
}
