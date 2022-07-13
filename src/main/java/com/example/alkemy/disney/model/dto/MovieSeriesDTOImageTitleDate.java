package com.example.alkemy.disney.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieSeriesDTOImageTitleDate {
    private String image;
    private String title;
    private LocalDate creationDate;
}
