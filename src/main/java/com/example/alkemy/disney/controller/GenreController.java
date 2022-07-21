package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.service.GenreServiceInterface;
import com.example.alkemy.disney.service.implementation.GenreServiceImplementation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreServiceInterface genreService;

    @Autowired
    public GenreController(GenreServiceImplementation genreService) {
        this.genreService = genreService;
    }

    @PostMapping()
    @ApiOperation("Endpoint to create new genres")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Genre created successfully"),
            @ApiResponse(code = 400, message = "Autoincrement ID, no needed")
    })
    public ResponseEntity<?> create(@RequestBody GenreDTO genreDTO){
        return new ResponseEntity<>(genreService.createGenre(genreDTO), HttpStatus.CREATED);
    }
}
