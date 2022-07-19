package com.example.alkemy.disney.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Set<Long> moviesSeries;
    private String order;


    public Boolean isASC(){
        return  order.compareToIgnoreCase("ASC") == 0;
    }

    public Boolean isDESC(){
        return  order.compareToIgnoreCase("DESC") == 0;
    }
}
