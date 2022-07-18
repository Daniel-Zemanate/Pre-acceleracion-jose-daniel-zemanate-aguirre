package com.example.alkemy.disney.service;

import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.model.entity.GenreEntity;

public interface GenreServiceInterface {
    GenreDTO createGenre(GenreDTO genreDTO);
    GenreDTO readGenreById(Long id);
    GenreEntity readGenreEntityById(Long id);


}
