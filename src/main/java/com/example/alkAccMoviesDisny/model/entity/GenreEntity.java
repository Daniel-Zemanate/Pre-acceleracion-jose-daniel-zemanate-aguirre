package com.example.alkAccMoviesDisny.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "genre")
public class GenreEntity {

    @Id
    @SequenceGenerator(name = "sec_genre", sequenceName = "sec_genre", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_genre")
    @Column(name = "id_genre")
    private Long idGenre;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

//    @OneToMany(mappedBy = "genre")
//    @JsonIgnore
//    private Set<MovieSeriesEntity> movieSeriesList;
}
