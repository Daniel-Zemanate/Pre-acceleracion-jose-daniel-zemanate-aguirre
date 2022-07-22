package com.example.alkemy.disney.configuration.security.controller;

import com.example.alkemy.disney.configuration.security.jwt.JwtUtil;
import com.example.alkemy.disney.configuration.security.model.AuthenticationRequest;
import com.example.alkemy.disney.configuration.security.model.AuthenticationResponse;
import com.example.alkemy.disney.configuration.security.model.dto.UserDTO;
import com.example.alkemy.disney.configuration.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private AuthenticationService authenticationService;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, AuthenticationService authenticationService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createAppUser(@RequestBody UserDTO userDTO) throws Exception{

        authenticationService.saveUser(userDTO);

        return new ResponseEntity<>("USER SUCCESSFULLY CREATED", HttpStatus.CREATED);
    }


}
