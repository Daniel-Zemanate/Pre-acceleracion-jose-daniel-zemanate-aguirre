package com.example.alkAccMoviesDisny.service;

import com.example.alkAccMoviesDisny.model.dto.GenreDTO;
import org.springframework.stereotype.Service;

public interface GenreServiceInterface {
    GenreDTO create(GenreDTO genreDTO);
}
