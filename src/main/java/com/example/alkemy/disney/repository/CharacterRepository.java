package com.example.alkemy.disney.repository;

import com.example.alkemy.disney.model.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
