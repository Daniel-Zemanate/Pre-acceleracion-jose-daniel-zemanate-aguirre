package com.example.alkemy.disney.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "GenreDTO will serve with all necessary to receive new properties or show up actual info. Read carefully and be aware of type/ format properties")
public class GenreDTO {
    private Long idGenre;

    @NotNull(message = "genre name must not be null")
    @NotBlank(message = "genre must have some name")
    private String name;

    @NotNull(message = "genre image must not be null")
    private String image;
}
