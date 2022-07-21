package com.example.alkemy.disney.repository;

import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieSeriesRepository extends JpaRepository<MovieSeriesEntity, Long>, JpaSpecificationExecutor<MovieSeriesEntity> {

    List<MovieSeriesEntity> findAll(Specification<MovieSeriesEntity> movieSeriesEntitySpecification);
}
