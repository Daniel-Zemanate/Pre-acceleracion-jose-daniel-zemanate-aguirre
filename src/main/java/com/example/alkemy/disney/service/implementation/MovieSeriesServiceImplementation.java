package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.exception.MyCreationWithIdException;
import com.example.alkemy.disney.exception.MyNoIdUpdateException;
import com.example.alkemy.disney.exception.MyNoNeedCharactersException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.entity.GenreEntity;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import com.example.alkemy.disney.model.mapper.CharacterMapper;
import com.example.alkemy.disney.model.mapper.MovieSeriesMapper;
import com.example.alkemy.disney.repository.MovieSeriesRepository;
import com.example.alkemy.disney.service.CharacterServiceInterface;
import com.example.alkemy.disney.service.GenreServiceInterface;
import com.example.alkemy.disney.service.MovieSeriesServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieSeriesServiceImplementation implements MovieSeriesServiceInterface {

    private MovieSeriesRepository movieSeriesRepository;
    private MovieSeriesMapper movieSeriesMapper;
    private GenreServiceInterface genreService;
    private CharacterMapper characterMapper;
    private CharacterServiceInterface characterService;

    public MovieSeriesServiceImplementation(MovieSeriesRepository movieSeriesRepository, MovieSeriesMapper movieSeriesMapper, GenreServiceInterface genreService, CharacterMapper characterMapper, CharacterServiceInterface characterService) {
        this.movieSeriesRepository = movieSeriesRepository;
        this.movieSeriesMapper = movieSeriesMapper;
        this.genreService = genreService;
        this.characterMapper = characterMapper;
        this.characterService = characterService;
    }

    @Override
    public MovieSeriesDTO createMovieSeries(MovieSeriesDTO movieSeriesDTO, Boolean isAnUpdate) {
        //VALIDATION: AUTOINCREMENT ID. NO NEEDED.
        if (movieSeriesDTO.getIdMovieSeries() != null && !isAnUpdate){
            throw new MyCreationWithIdException("Something went wrong when createMovieSeries in -MovieSeriesServiceImplementation-");
        }

        GenreEntity genreEntity = genreService.readGenreEntityById(movieSeriesDTO.getGenreId());
        movieSeriesDTO.setGenre(genreService.readGenreById(movieSeriesDTO.getGenreId()));

        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();

        //-------------
        if (!isAnUpdate){
            for (CharacterDTO characterDTO : movieSeriesDTO.getCharacters()) {
                if (characterDTO.getIdCharacter() != null){

                    throw new MyCreationWithIdException("Something went wrong when verifying characters id in createMovieSeries in -MovieSeriesServiceImplementation-");
                }
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

            throw new MyNotFoundIdException("Something went wrong when readMovieSeriesById: "+id+" in -MovieSeriesServiceImplementation-");
        }

        MovieSeriesDTO responseMovieSeriesDTO = movieSeriesMapper.toMovieSeriesDTO(entityCheck.get(), true);

        return responseMovieSeriesDTO;
    }

    @Override
    public MovieSeriesDTO updateMovieSeries(MovieSeriesDTO movieSeriesDTO) {
        if (movieSeriesDTO.getIdMovieSeries() == null){

            throw new MyNoIdUpdateException("Something went wrong when updateMovieSeries in -MovieSeriesServiceImplementation-");
        }
        //VALIDATION: ID EXISTS.
        MovieSeriesDTO movieSeriesDTOUpdate = readMovieSeriesById(movieSeriesDTO.getIdMovieSeries());

        movieSeriesDTOUpdate.setImage(movieSeriesDTO.getImage());
        movieSeriesDTOUpdate.setTitle(movieSeriesDTO.getTitle());
        movieSeriesDTOUpdate.setCreationDate(movieSeriesDTO.getCreationDate());
        movieSeriesDTOUpdate.setGrade(movieSeriesDTO.getGrade());
        movieSeriesDTOUpdate.setGenreId(movieSeriesDTO.getGenreId());

        if (movieSeriesDTO.getCharacters().size() != 0){
            if (movieSeriesDTOUpdate.getCharacters().size() == movieSeriesDTO.getCharacters().size()){
                int comparison = 0;

                for (CharacterDTO characterDTO : movieSeriesDTO.getCharacters()) {
                    if (movieSeriesDTOUpdate.getCharacters().contains(characterDTO)){
                        comparison++;
                    }
                }

                if (comparison != movieSeriesDTOUpdate.getCharacters().size()){

                    throw new MyNoNeedCharactersException("Something went wrong when updateMovieSeries in -MovieSeriesServiceImplementation-");
                }
            }else {

                throw new MyNoNeedCharactersException("Something went wrong when updateMovieSeries in -MovieSeriesServiceImplementation-");
            }
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
        responseMovieSeriesDTO.setGenreId(responseMovieSeriesDTO.getGenre().getIdGenre());
        CharacterDTO characterDTO = characterService.readCharacterById(idCharacter);

        if (!responseMovieSeriesDTO.getCharacters().contains(characterDTO)){
            responseMovieSeriesDTO.getCharacters().add(characterDTO);
            responseMovieSeriesDTO = createMovieSeries(responseMovieSeriesDTO, true);
        }

        return responseMovieSeriesDTO;
    }
}
