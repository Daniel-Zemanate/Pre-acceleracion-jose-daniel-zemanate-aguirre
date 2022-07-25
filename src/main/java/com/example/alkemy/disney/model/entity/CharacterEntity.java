package com.example.alkemy.disney.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id_character = ?")
@Where(clause = "deleted = false")
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

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "characters")
    @JsonManagedReference
    private List<MovieSeriesEntity> movieSeriesList;
}
