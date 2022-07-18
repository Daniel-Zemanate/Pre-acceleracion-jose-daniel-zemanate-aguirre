package com.example.alkemy.disney;

import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.entity.GenreEntity;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import com.example.alkemy.disney.repository.CharacterRepository;
import com.example.alkemy.disney.repository.GenreRepository;
import com.example.alkemy.disney.repository.MovieSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private MovieSeriesRepository movieSeriesRepository;
    private CharacterRepository characterRepository;
    private GenreRepository genreRepository;

    @Autowired
    public DataLoader(MovieSeriesRepository movieSeriesRepository, CharacterRepository characterRepository, GenreRepository genreRepository) {
        this.movieSeriesRepository = movieSeriesRepository;
        this.characterRepository = characterRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage("image1");
        genreEntity.setName("name1");
        genreRepository.save(genreEntity);


        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage("image1");
        characterEntity.setName("name1");
        characterEntity.setAge(1);
        characterEntity.setWeight(1);
        characterEntity.setHistory("history1");
        characterRepository.save(characterEntity);

        CharacterEntity characterEntity2 = new CharacterEntity();
        characterEntity2.setImage("image2");
        characterEntity2.setName("name2");
        characterEntity2.setAge(2);
        characterEntity2.setWeight(2);
        characterEntity2.setHistory("history2");

        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();
        Set<CharacterEntity> characterEntitySet = new HashSet<>();
        characterEntitySet.add(characterEntity2);

        movieSeriesEntity.setImage("image1");
        movieSeriesEntity.setTitle("title1");
        movieSeriesEntity.setCreationDate(LocalDate.now());
        movieSeriesEntity.setGrade(1);
//        movieSeriesEntity.setCharacters(characterEntitySet);
        movieSeriesEntity.setGenreId(1L);
        movieSeriesRepository.save(movieSeriesEntity);
    }
}
