package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.service.CharacterServiceInterface;
import com.example.alkemy.disney.service.implementation.CharacterServiceImplementation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private CharacterServiceInterface characterService;

    @Autowired
    public CharacterController(CharacterServiceImplementation characterService) {
        this.characterService = characterService;
    }

    @PostMapping()
    @ApiOperation("Endpoint to create new characters")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Character successfully created"),
            @ApiResponse(code = 400, message = "Wrong request params")
    })
    public ResponseEntity<?> createCharacter(@Valid @RequestBody CharacterDTO characterDTO){

        return new ResponseEntity<>(characterService.createCharacter(characterDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Endpoint to request for specific character details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character successfully read"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> readCharacterById(@PathVariable("id") Long id){

        return new ResponseEntity<>(characterService.readCharacterById(id), HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation("Endpoint to update characters")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character successfully updated"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO){

        return new ResponseEntity<>(characterService.updateCharacter(characterDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Endpoint to delete a specific character")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character successfully deleted"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Search parameter Not found")
    })
    public ResponseEntity<?> deleteCharacter(@PathVariable("id") Long id){

        return new ResponseEntity<>(characterService.deleteCharacterById(id), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Endpoint to request a list of characters with image and name properties. Filters like name, age, weight and movies participation could be applied")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of characters successfully read")
    })
    public ResponseEntity<?> readCharactersWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer weight,
            @RequestParam(required = false) Set<Long> moviesSeries){

        return new ResponseEntity<>(characterService.readCharactersWithFilters(name, age, weight, moviesSeries), HttpStatus.OK);
    }

}
