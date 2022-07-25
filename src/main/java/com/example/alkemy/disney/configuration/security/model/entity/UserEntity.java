package com.example.alkemy.disney.configuration.security.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "app_users")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "sec_user", sequenceName = "sec_user", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_user")
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    public UserEntity(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }


    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
