package com.example.alkemy.disney.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "movie_series")
public class MovieSeriesEntity {

    @Id
    @SequenceGenerator(name = "sec_movie_series", sequenceName = "sec_movie_series", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_movie_series")
    @Column(name = "id_movie_series")
    private Long idMovieSeries;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "grade")
    private Integer grade;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "movie_series_character", joinColumns = @JoinColumn(name = "movie_series_id", foreignKey = @ForeignKey(name = "fk_movie_series_id")), inverseJoinColumns = @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "fk_character_id")))
    @JsonManagedReference
    private Set<CharacterEntity> characters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_genre_id"))
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;


    public void addCharacter(CharacterEntity characterEntity){
        characters.add(characterEntity);
    }
    public void deleteCharacter(CharacterEntity characterEntity){
        characters.remove(characterEntity);
    }
}
