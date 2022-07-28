package com.example.alkemy.disney.configuration.security.service;

import com.example.alkemy.disney.configuration.security.model.dto.UserDTO;
import com.example.alkemy.disney.configuration.security.model.entity.UserEntity;
import com.example.alkemy.disney.configuration.security.repository.UserRepository;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.exception.MyUniqueControlException;
import com.example.alkemy.disney.service.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {


    private UserRepository userRepository;

    private EmailServiceInterface emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public AuthenticationService(UserRepository userRepository, EmailServiceInterface emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (!user.isPresent()){

//            throw new UsernameNotFoundException("Not found by username");
            throw new MyNotFoundIdException("can not find any username= \""+username+"\" ");
        }

        UserDetails userDetail = new User(user.get().getUsername(), user.get().getPassword(),true, true, true,true, Collections.emptyList());

        return userDetail;
    }

    public void saveUser(UserDTO userDTO){
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()){

            throw new MyUniqueControlException("Username: " + userDTO.getUsername() + " already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(userEntity);

        //Email service to Welcome new users, disable by default.
        emailService.sendWelcomeEmailTo(userEntity.getUsername());
    }
}
