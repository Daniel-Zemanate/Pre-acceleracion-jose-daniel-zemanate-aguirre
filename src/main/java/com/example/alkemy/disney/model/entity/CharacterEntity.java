package com.example.alkemy.disney.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "character")
public class CharacterEntity {
    @Id
    @SequenceGenerator(name = "sec_character", sequenceName = "sec_character", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_character")
    @Column(name = "id_character")
    private Long idCharacter;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "story")
    private String story;

    @ManyToMany(mappedBy = "characters")
    @JsonManagedReference
    private List<MovieSeriesEntity> movieSeriesList;
}
