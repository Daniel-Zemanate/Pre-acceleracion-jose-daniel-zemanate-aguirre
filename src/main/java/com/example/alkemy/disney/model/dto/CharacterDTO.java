package com.example.alkemy.disney.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@ApiModel(description = "CharacterDTO will serve with all necessary to receive new properties or show up actual info. Read carefully and be aware of type/ format properties")
public class CharacterDTO {

    @ApiParam("send null when executing new. send any require number when executing update")
    private Long idCharacter;

    @NotNull(message = "character image must not be null")
    private String image;

    @NotNull(message = "character name must not be null")
    @NotBlank(message = "character must have some name")
    private String name;

    @PositiveOrZero(message = "character age must be a positive number of years")
    private Integer age;

    @PositiveOrZero(message = "character weight must be a positive number of kilograms")
    private Integer weight;

    @NotNull(message = "character story must not be null")
    @NotBlank(message = "character must have some story")
    private String story;

    @Null(message = "character must not include movies/series")
    private List<MovieSeriesDTO> movieSeriesList;

}
