package com.example.alkemy.disney.configuration.security.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserDTO {

    @Email(message = "username must be a valid email")
    @NotNull(message = "username must not be null")
    @Pattern(regexp = "^((.([a-zA-Z0-9]+(([.]){1})?)+))([@]{1})((.([a-zA-Z0-9]+))(([.]){1}))(.([.a-zA-Z0-9]){1,5})$", message = "Must be a valid email; " +
            "e.g: validemail@domain.com.co; e.g: rightemail@domain.com")
    private String username;

//    @Size(min = 8, message = "password must have had minimum 8 characters")
    @NotNull(message = "password must not be null")
    @Pattern(regexp = "^(?=.*[A-Z]{1})(?=.*[\\W]{1})(?=.*[0-9]{1})(?=.*[a-z]{1}).{8,25}$", message = "Rules; 1 Uppercase letter (A); " +"1 Symbol (! \" # $ % & ' ( ) * +); " + "1 Lowercase letter (z); " + "1 digit (0-9); " + "minimum length of 8 characters")
    private String password;
//    private Role role;
}
