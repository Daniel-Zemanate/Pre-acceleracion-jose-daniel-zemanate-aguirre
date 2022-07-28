package com.example.alkemy.disney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "MovieSeriesDTO will serve with all necessary to receive new properties or show up actual info. Read carefully and be aware of type/ format properties")
public class MovieSeriesDTO {

    @ApiParam("send null when executing new. send any require number when executing update")
    private Long idMovieSeries;

    @NotNull(message = "movie/ series image must not be null")
    private String image;

    @NotNull(message = "movie/ series title must not be null")
    @NotBlank(message = "movie/ series must have some title")
    private String title;

    @ApiParam("date format should be: yyyy-MM-dd")
    private LocalDate creationDate;

    @PositiveOrZero(message = "movie/ series grade must be a positive number")
    @Max(value = 5, message = "grade can be score with maximum 5")
    private Integer grade;

    @Valid
    private Set<CharacterDTO> characters;
    private GenreDTO genre;

    @PositiveOrZero(message = "movie/ series genre identifier must be a positive number")
    @NotNull(message = "movie/ series genre identifier must not be null")
    @ApiParam("genreId has the main function when executing new/ update Entity. Avoid to describe any value for property \"genre\" ")
    private Long genreId;
}
