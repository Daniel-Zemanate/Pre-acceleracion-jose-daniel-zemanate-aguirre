package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.MovieSeriesDTO;
import com.example.alkemy.disney.service.MovieSeriesServiceInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieSeriesController {

    private MovieSeriesServiceInterface movieSeriesService;

    @Autowired
    public MovieSeriesController(MovieSeriesServiceInterface movieSeriesService) {
        this.movieSeriesService = movieSeriesService;
    }

    @PostMapping("/create")
    @ApiOperation("Endpoint to create new movies/series")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Movie/Series created successfully"),
            @ApiResponse(code = 400, message = "Autoincrement ID, no needed / Null genre id"),
            @ApiResponse(code = 404, message = "Input genre id not found")
    })
    public ResponseEntity<?> createMovieSeries(@RequestBody MovieSeriesDTO movieSeriesDTO){

        return new ResponseEntity<>(movieSeriesService.createMovieSeries(movieSeriesDTO, false), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    @ApiOperation("Endpoint to request for specific movie/serie details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character read successfully"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> readMovieSeriesById(@PathVariable("id") Long id){

        return new ResponseEntity<>(movieSeriesService.readMovieSeriesById(id), HttpStatus.OK);
    }
    @PutMapping("/update")
    @ApiOperation("Endpoint to update movies/series")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Movie/Series updated successfully"),
            @ApiResponse(code = 400, message = "Null id or ID type is not Long"),
            @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> updateMovieSeries(@RequestBody MovieSeriesDTO movieSeriesDTO){

        return new ResponseEntity<>(movieSeriesService.updateMovieSeries(movieSeriesDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("Endpoint to delete a specific movie/series")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Movie/Series deleted successfully"),
        @ApiResponse(code = 400, message = "Wrong request params"),
        @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> deleteMovieSeries(@PathVariable("id") Long id){

        return new ResponseEntity<>(movieSeriesService.deleteMovieSeries(id), HttpStatus.OK);
    }


    @PostMapping("/{idMovie}/characters/{idCharacter}")
    @ApiOperation("Endpoint to Add one character to a movie/series")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character successfully added to Movie/Series"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameters Not found")
    })
    public ResponseEntity<?> addCharacterToMovieSeries(@PathVariable("idMovie") Long idMovie, @PathVariable("idCharacter") Long idCharacter){

        return new ResponseEntity<>(movieSeriesService.addCharacterToMovieSeries(idMovie, idCharacter), HttpStatus.OK);
    }


    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    @ApiOperation("Endpoint to Remove one character from a movie/series")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character successfully removed from Movie/Series"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameters Not found")
    })
    public ResponseEntity<?> removeCharacterFromMovieSeries(@PathVariable("idMovie") Long idMovie, @PathVariable("idCharacter") Long idCharacter){

        return new ResponseEntity<>(movieSeriesService.removeCharacterToMovieSeries(idMovie, idCharacter), HttpStatus.OK);
    }

}
