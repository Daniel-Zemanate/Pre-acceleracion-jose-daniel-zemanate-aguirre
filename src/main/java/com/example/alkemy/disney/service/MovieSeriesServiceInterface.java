package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.model.dto.MovieSeriesDTOCreateUpdate;

public interface MovieSeriesServiceInterface {
    MovieSeriesDTO createMovieSeries(MovieSeriesDTOCreateUpdate movieSeriesDTO);
}
