package com.example.alkemy.disney.repository.specification;

import com.example.alkemy.disney.model.dto.MovieSeriesFiltersDTO;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSeriesSpecification {

    public Specification<MovieSeriesEntity> readByFilters(MovieSeriesFiltersDTO movieSeriesFiltersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(movieSeriesFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")), "%" + movieSeriesFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (movieSeriesFiltersDTO.getGenreId() != null){

                predicates.add(
                        criteriaBuilder.equal(root.get("genreId"), movieSeriesFiltersDTO.getGenreId())
                );
            }

            query.distinct(true);


            String orderByField = "creationDate";
            query.orderBy(
                    movieSeriesFiltersDTO.isASC() ? criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
