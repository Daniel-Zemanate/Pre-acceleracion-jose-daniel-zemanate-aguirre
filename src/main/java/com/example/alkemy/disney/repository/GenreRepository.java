package com.example.alkemy.disney.repository;

import com.example.alkemy.disney.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
