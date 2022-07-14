package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.MovieSeriesDTOCreateUpdate;
import com.example.alkemy.disney.service.MovieSeriesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieSeriesController {

    private MovieSeriesServiceInterface movieSeriesService;

    @Autowired
    public MovieSeriesController(MovieSeriesServiceInterface movieSeriesService) {
        this.movieSeriesService = movieSeriesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovieSeries(@RequestBody MovieSeriesDTOCreateUpdate movieSeriesDTO){

        return new ResponseEntity<>(movieSeriesService.createMovieSeries(movieSeriesDTO), HttpStatus.CREATED);
    }
}
