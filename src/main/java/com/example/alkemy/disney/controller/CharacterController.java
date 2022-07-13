package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.model.dto.CharacterDTO;
import com.example.alkemy.disney.model.dto.CharacterDTOCreateUpdate;
import com.example.alkemy.disney.service.implementation.CharacterServiceImplementation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private CharacterServiceImplementation characterService;

    @Autowired
    public CharacterController(CharacterServiceImplementation characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/create")
    @ApiOperation("Endpoint to create new characters")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Character created successfully"),
            @ApiResponse(code = 400, message = "Autoincrement ID, no needed")
    })
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDTOCreateUpdate characterDTOCreateUpdate){

        return new ResponseEntity<>(characterService.createCharacter(characterDTOCreateUpdate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Endpoint to request for specific character details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Character read successfully"),
            @ApiResponse(code = 400, message = "Wrong request params"),
            @ApiResponse(code = 404, message = "Not found search param")
    })
    public ResponseEntity<?> readCharacterById(@PathVariable("id") Long id){

        return new ResponseEntity<>(characterService.readCharacterById(id), HttpStatus.OK);
    }
}
