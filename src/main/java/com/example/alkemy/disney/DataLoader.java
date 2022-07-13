package com.example.alkemy.disney;

import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class DataLoader implements ApplicationRunner {

    private CharacterRepository characterRepository;

    @Autowired
    public DataLoader(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage("image1");
        characterEntity.setName("name1");
        characterEntity.setAge(1);
        characterEntity.setWeight(1);
        characterEntity.setHistory("history1");
        characterRepository.save(characterEntity);
    }
}
