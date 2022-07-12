package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.service.GenreServiceInterface;
import com.example.alkemy.disney.model.mapper.GenreMapper;
import com.example.alkemy.disney.repository.GenreRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImplementation implements GenreServiceInterface {

    final static Logger LOGGER = Logger.getLogger(GenreServiceImplementation.class);
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;

    @Autowired
    public GenreServiceImplementation(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        LOGGER.info("Executing genre creation in class: GenreServiceImplementation");
        return genreMapper.toGenreDTO(genreRepository.save(genreMapper.toGenreEntity(genreDTO)));
    }
}
