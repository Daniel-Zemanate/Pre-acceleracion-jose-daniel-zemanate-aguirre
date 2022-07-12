package com.example.alkemy.disney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private CharacterServiceImplementation characterService;

    @Autowired
    public CharacterController(CharacterServiceImplementation characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CharacterDTO characterDTO){
        return new ResponseEntity<>()

    }
}
