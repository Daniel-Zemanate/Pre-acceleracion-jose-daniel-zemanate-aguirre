package com.example.alkemy.disney.configuration.security.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {

    @Email(message = "username must be a valid email")
    @NotNull(message = "username must not be null")
    private String username;

    @Size(min = 8, message = "password must have had minimum 8 characters")
    @NotNull(message = "password must not be null")
    private String password;
//    private Role role;
}
