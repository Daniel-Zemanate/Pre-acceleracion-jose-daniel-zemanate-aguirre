package com.example.alkemy.disney.repository;

import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSeriesRepository extends JpaRepository<MovieSeriesEntity, Long> {
}
