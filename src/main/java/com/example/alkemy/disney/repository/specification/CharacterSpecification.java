package com.example.alkemy.disney.repository.specification;

import com.example.alkemy.disney.model.dto.CharacterFiltersDTO;
import com.example.alkemy.disney.model.entity.CharacterEntity;
import com.example.alkemy.disney.model.entity.MovieSeriesEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> readByFilters(CharacterFiltersDTO characterFiltersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(characterFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")), "%" + characterFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (characterFiltersDTO.getAge() != null && characterFiltersDTO.getAge()>=0){

                predicates.add(
                        criteriaBuilder.equal(root.get("age"), characterFiltersDTO.getAge())
                );
            }

            if (!CollectionUtils.isEmpty(characterFiltersDTO.getMoviesSeries())){
                Join<MovieSeriesEntity, CharacterEntity> join = root.join("movieSeriesList", JoinType.INNER);
                Expression<String> charactersId = join.get("idCharacter");
                predicates.add(charactersId.in(characterFiltersDTO.getMoviesSeries()));
            }

            query.distinct(true);


            String orderByField = "name";
            query.orderBy(
                    characterFiltersDTO.isASC() ? criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));




        });
    }
}
