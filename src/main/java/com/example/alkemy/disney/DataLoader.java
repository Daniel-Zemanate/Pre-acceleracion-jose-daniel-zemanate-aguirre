package com.example.alkemy.disney;

import com.example.alkemy.disney.configuration.security.model.entity.UserEntity;
import com.example.alkemy.disney.configuration.security.repository.UserRepository;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.entity.GenreEntity;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import com.example.alkemy.disney.repository.CharacterRepository;
import com.example.alkemy.disney.repository.GenreRepository;
import com.example.alkemy.disney.repository.MovieSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    private MovieSeriesRepository movieSeriesRepository;
    private CharacterRepository characterRepository;
    private GenreRepository genreRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(MovieSeriesRepository movieSeriesRepository, CharacterRepository characterRepository, GenreRepository genreRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.movieSeriesRepository = movieSeriesRepository;
        this.characterRepository = characterRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Autowired
//    public DataLoader(MovieSeriesRepository movieSeriesRepository, CharacterRepository characterRepository, GenreRepository genreRepository, UserRepository userRepository) {
//        this.movieSeriesRepository = movieSeriesRepository;
//        this.characterRepository = characterRepository;
//        this.genreRepository = genreRepository;
//        this.userRepository = userRepository;
//    }

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
        characterEntity.setStory("history1");
        characterRepository.save(characterEntity);
        CharacterEntity characterEntity2 = new CharacterEntity();
        characterEntity2.setImage("image2");
        characterEntity2.setName("name2");
        characterEntity2.setAge(2);
        characterEntity2.setWeight(2);
        characterEntity2.setStory("history2");
        characterRepository.save(characterEntity2);
        CharacterEntity characterEntity3 = new CharacterEntity();
        characterEntity3.setImage("image3");
        characterEntity3.setName("name3");
        characterEntity3.setAge(3);
        characterEntity3.setWeight(3);
        characterEntity3.setStory("history3");
        characterRepository.save(characterEntity3);

        MovieSeriesEntity movieSeriesEntity = new MovieSeriesEntity();
//        Set<CharacterEntity> characterEntitySet = new HashSet<>();
//        characterEntitySet.add(characterEntity2);

        movieSeriesEntity.setImage("image1");
        movieSeriesEntity.setTitle("title1");
        movieSeriesEntity.setCreationDate(LocalDate.now());
        movieSeriesEntity.setGrade(1);
//        movieSeriesEntity.setCharacters(characterEntitySet);
        movieSeriesEntity.setGenreId(1L);
        movieSeriesRepository.save(movieSeriesEntity);

        MovieSeriesEntity movieSeriesEntity2 = new MovieSeriesEntity();

        movieSeriesEntity2.setImage("image2");
        movieSeriesEntity2.setTitle("title2");
        movieSeriesEntity2.setCreationDate(LocalDate.now());
        movieSeriesEntity2.setGrade(2);
//        movieSeriesEntity2.setCharacters(characterEntitySet);
        movieSeriesEntity2.setGenreId(1L);
        movieSeriesRepository.save(movieSeriesEntity2);


//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String password = passwordEncoder.encode("user1");
        UserEntity userEntity = new UserEntity("user1", password);
        userRepository.save(userEntity);

    }
}
