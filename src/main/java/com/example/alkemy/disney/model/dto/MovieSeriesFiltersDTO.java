package com.example.alkemy.disney.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSeriesFiltersDTO {

    private String name;
    private Long genreId;
    private String order;

    public Boolean isASC(){
        return  order.compareToIgnoreCase("ASC") == 0;
    }

    public Boolean isDESC(){
        return  order.compareToIgnoreCase("DESC") == 0;
    }

}
