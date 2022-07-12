package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.service.implementation.GenreServiceImplementation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

    private GenreServiceImplementation genreService;

    @Autowired
    public GenreController(GenreServiceImplementation genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/create")
    @ApiOperation("Endpoint to create new genres")
    @ApiResponse(code = 201, message = "Genre created successfully")
    public ResponseEntity<?> create(@RequestBody GenreDTO genreDTO){
        return new ResponseEntity<>(genreService.create(genreDTO), HttpStatus.CREATED);
    }
}
