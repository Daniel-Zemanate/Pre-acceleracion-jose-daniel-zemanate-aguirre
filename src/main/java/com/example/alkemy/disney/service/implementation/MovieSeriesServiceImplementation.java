package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.exception.MyEntityIdControlException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.model.dto.MovieSeriesDTOImageTitleDate;
import com.example.alkemy.disney.model.dto.MovieSeriesFiltersDTO;
import com.example.alkemy.disney.model.entity.GenreEntity;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import com.example.alkemy.disney.model.mapper.MovieSeriesMapper;
import com.example.alkemy.disney.repository.MovieSeriesRepository;
import com.example.alkemy.disney.repository.specification.MovieSeriesSpecification;
import com.example.alkemy.disney.service.CharacterServiceInterface;
import com.example.alkemy.disney.service.GenreServiceInterface;
import com.example.alkemy.disney.service.MovieSeriesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieSeriesServiceImplementation implements MovieSeriesServiceInterface {

    private MovieSeriesRepository movieSeriesRepository;
    private MovieSeriesMapper movieSeriesMapper;
    private GenreServiceInterface genreService;
    private CharacterServiceInterface characterService;

    private MovieSeriesSpecification movieSeriesSpecification;

    @Autowired
    public MovieSeriesServiceImplementation(MovieSeriesRepository movieSeriesRepository, MovieSeriesMapper movieSeriesMapper, GenreServiceInterface genreService, CharacterServiceInterface characterService, MovieSeriesSpecification movieSeriesSpecification) {
        this.movieSeriesRepository = movieSeriesRepository;
        this.movieSeriesMapper = movieSeriesMapper;
        this.genreService = genreService;
        this.characterService = characterService;
        this.movieSeriesSpecification = movieSeriesSpecification;
    }

    @Override
    public MovieSeriesDTO createMovieSeries(MovieSeriesDTO movieSeriesDTO, Boolean isAnUpdate) {
        //VALIDATION: AUTOINCREMENT ID. NO NEEDED.
        if (movieSeriesDTO.getIdMovieSeries() != null && !isAnUpdate){

            throw new MyEntityIdControlException("Trying to create a movie/series with id= "+ movieSeriesDTO.getIdMovieSeries() +" included");
        }

        GenreEntity genreEntity = genreService.readGenreEntityById(movieSeriesDTO.getGenreId());
        movieSeriesDTO.setGenre(genreService.readGenreById(movieSeriesDTO.getGenreId()));

        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();

        //-------------
        if (!isAnUpdate){
            List<Long> idCharactersCheck = new ArrayList<>();

            for (CharacterDTO characterDTO : movieSeriesDTO.getCharacters()) {
                if (characterDTO.getIdCharacter() != null){

                    idCharactersCheck.add(characterDTO.getIdCharacter());
                }
            }

            if (!idCharactersCheck.isEmpty()){

                throw new MyEntityIdControlException("Trying to create a character with id= "+ idCharactersCheck +" included");
            }

        }else {
            //CLEANING OLD RECORDS
            movieSeriesEntity = movieSeriesMapper.toMovieSeriesEntity(movieSeriesDTO, false);
            movieSeriesRepository.save(movieSeriesEntity);
        }
        //-------------

        //SAVING NEW RECORDS WITH CHARACTERS
        movieSeriesEntity = movieSeriesRepository.save(movieSeriesMapper.toMovieSeriesEntity(movieSeriesDTO, true));
        movieSeriesEntity.setGenre(genreEntity);


        MovieSeriesDTO responseMovieSeriesDTO = movieSeriesMapper.toMovieSeriesDTO(movieSeriesEntity, true);

        return responseMovieSeriesDTO;
    }

    @Override
    public MovieSeriesDTO readMovieSeriesById(Long id) {
        Optional<MovieSeriesEntity> entityCheck = movieSeriesRepository.findById(id);

        if (!entityCheck.isPresent()){

            throw new MyNotFoundIdException("can not find any movie/series with id= "+ id);
        }

        MovieSeriesDTO responseMovieSeriesDTO = movieSeriesMapper.toMovieSeriesDTO(entityCheck.get(), true);

        return responseMovieSeriesDTO;
    }

    @Override
    public MovieSeriesDTO updateMovieSeries(MovieSeriesDTO movieSeriesDTO) {
        if (movieSeriesDTO.getIdMovieSeries() == null){

            throw new MyEntityIdControlException("Trying to update a character with id= "+ movieSeriesDTO.getIdMovieSeries());
        }
        //VALIDATION: ID EXISTS.
        MovieSeriesDTO movieSeriesDTOUpdate = readMovieSeriesById(movieSeriesDTO.getIdMovieSeries());

        movieSeriesDTOUpdate.setImage(movieSeriesDTO.getImage());
        movieSeriesDTOUpdate.setTitle(movieSeriesDTO.getTitle());
        movieSeriesDTOUpdate.setCreationDate(movieSeriesDTO.getCreationDate());
        movieSeriesDTOUpdate.setGrade(movieSeriesDTO.getGrade());
        movieSeriesDTOUpdate.setGenreId(movieSeriesDTO.getGenreId());

        if (movieSeriesDTO.getCharacters().size() != 0){

            throw new MyEntityIdControlException("Only movie/series properties are allowed to be update, list of characters are not allowed");
        }

        return createMovieSeries(movieSeriesDTOUpdate, true);
    }

    @Override
    public MovieSeriesDTO deleteMovieSeries(Long id) {
        MovieSeriesDTO responseMovieSeriesDTO = readMovieSeriesById(id);

        movieSeriesRepository.deleteById(id);

        return responseMovieSeriesDTO;
    }

    @Override
    public MovieSeriesDTO addCharacterToMovieSeries(Long idMovie, Long idCharacter) {
        MovieSeriesDTO responseMovieSeriesDTO = readMovieSeriesById(idMovie);
        CharacterDTO characterDTO = characterService.readCharacterById(idCharacter);

        boolean contains = false;
        for (CharacterDTO characterDTOCheck : responseMovieSeriesDTO.getCharacters()) {
            if (characterDTO.getIdCharacter() == characterDTOCheck.getIdCharacter()){

                contains = true;
            }
        }

        if (!contains){
            responseMovieSeriesDTO.setGenreId(responseMovieSeriesDTO.getGenre().getIdGenre());
            responseMovieSeriesDTO.getCharacters().add(characterDTO);
            responseMovieSeriesDTO = createMovieSeries(responseMovieSeriesDTO, true);
        }

        return responseMovieSeriesDTO;
    }

    @Override
    public MovieSeriesDTO removeCharacterToMovieSeries(Long idMovie, Long idCharacter) {
        MovieSeriesDTO responseMovieSeriesDTO = readMovieSeriesById(idMovie);
        CharacterDTO characterDTO = characterService.readCharacterById(idCharacter);

        boolean contains = false;
        for (CharacterDTO characterDTOCheck : responseMovieSeriesDTO.getCharacters()) {
            if (characterDTO.getIdCharacter() == characterDTOCheck.getIdCharacter()){
                characterDTO = characterDTOCheck;
                contains = true;
            }
        }

        if (contains){
            responseMovieSeriesDTO.setGenreId(responseMovieSeriesDTO.getGenre().getIdGenre());
            responseMovieSeriesDTO.getCharacters().remove(characterDTO);
            responseMovieSeriesDTO = createMovieSeries(responseMovieSeriesDTO, true);
        }

        return responseMovieSeriesDTO;
    }

    @Override
    public List<MovieSeriesDTOImageTitleDate> readMoviesSeriesWithFilters(String name, Long genreId, String order) {
        MovieSeriesFiltersDTO movieSeriesFiltersDTO = new MovieSeriesFiltersDTO(name, genreId, order);
        List<MovieSeriesEntity> movieSeriesEntityList = movieSeriesRepository.findAll(movieSeriesSpecification.readByFilters(movieSeriesFiltersDTO));

        return movieSeriesMapper.toListMovieSeriesDTOImageTitleDate(movieSeriesEntityList);
    }
}
