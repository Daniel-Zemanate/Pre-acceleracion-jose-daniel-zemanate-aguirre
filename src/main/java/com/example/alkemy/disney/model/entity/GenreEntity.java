package com.example.alkemy.disney.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id_genre = ?")
@Where(clause = "deleted = false")
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

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

//    @OneToMany(mappedBy = "genre")
//    @JsonIgnore
//    private Set<MovieSeriesEntity> movieSeriesList;
}
