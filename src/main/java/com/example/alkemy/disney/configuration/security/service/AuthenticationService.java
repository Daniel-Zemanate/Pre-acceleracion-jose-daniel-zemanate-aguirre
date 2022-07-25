package com.example.alkemy.disney.configuration.security.service;

import com.example.alkemy.disney.configuration.security.model.dto.UserDTO;
import com.example.alkemy.disney.configuration.security.model.entity.UserEntity;
import com.example.alkemy.disney.configuration.security.repository.UserRepository;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.exception.MyUniqueControlException;
import com.example.alkemy.disney.service.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {


    private UserRepository userRepository;

    private EmailServiceInterface emailService;

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
//        //ENABLING AUTHORIZATIONS FROM ROLES
//        Set<GrantedAuthority> authorizations = new HashSet<>();
//        GrantedAuthority authorization = null;
//
////        //SEVERAL ROLES FOR ONE USER
////        for (Rol rol : user.get().getRoles()) {
////            authorization = new SimpleGrantedAuthority(rol.getName());
////            authorizations.add(authorization);
////        }
//
//        //ONE ROLE FOR ONE USER
//        authorization = new SimpleGrantedAuthority("ROLE_" + user.get().getRole());
//
//        authorizations.add(authorization);

//        //Using "{noop}" before the password is used when encrypt is not implemented
//        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getUsername(),"{noop}" + user.get().getPassword(),true, true, true,true, Collections.emptyList());

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),true, true, true,true, Collections.emptyList());

        return userDetail;
    }

    public void saveUser(UserDTO userDTO){
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()){

            throw new MyUniqueControlException("Username: " + userDTO.getUsername() + " already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(userEntity);

        //Email service to Welcome new users, disable by default.
        emailService.sendWelcomeEmailTo(userEntity.getUsername());
    }
}
