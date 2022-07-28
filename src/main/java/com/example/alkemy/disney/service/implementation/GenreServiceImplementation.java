package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.exception.MyEntityIdControlException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.model.dto.GenreDTO;
import com.example.alkemy.disney.model.entity.GenreEntity;
import com.example.alkemy.disney.model.mapper.GenreMapper;
import com.example.alkemy.disney.repository.GenreRepository;
import com.example.alkemy.disney.service.GenreServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public GenreDTO createGenre(GenreDTO genreDTO) {
        LOGGER.info("Executing genre creation in class: GenreServiceImplementation");

        //VALIDATION: AUTOINCREMENT ID. NO NEEDED.
        if (genreDTO.getIdGenre() != null){

            throw new MyEntityIdControlException("Trying to create a genre with id= "+ genreDTO.getIdGenre() +" included");
        }

        return genreMapper.toGenreDTO(genreRepository.save(genreMapper.toGenreEntity(genreDTO)));
    }

    @Override
    public GenreDTO readGenreById(Long id) {
        GenreDTO genreDTO = genreMapper.toGenreDTO(readGenreEntityById(id));

        return genreDTO;
    }

    @Override
    public GenreEntity readGenreEntityById(Long id) {
        if (id == null){

            throw new MyEntityIdControlException("Trying to find a genre with id= "+ id);
        }

        Optional<GenreEntity> genreEntity = genreRepository.findById(id);

        if (!genreEntity.isPresent()){

            throw new MyNotFoundIdException("can not find any genre with id= "+ id);
        }

        return genreEntity.get();
    }


}
