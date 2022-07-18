package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.MovieSeriesDTO;

public interface MovieSeriesServiceInterface {
    MovieSeriesDTO createMovieSeries(MovieSeriesDTO movieSeriesDTO, Boolean isAnUpdate);
    MovieSeriesDTO readMovieSeriesById(Long id);

    MovieSeriesDTO updateMovieSeries(MovieSeriesDTO movieSeriesDTO);

    MovieSeriesDTO deleteMovieSeries(Long id);

    MovieSeriesDTO addCharacterToMovieSeries(Long idMovie, Long idCharacter);
}
