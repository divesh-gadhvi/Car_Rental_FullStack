package com.springproject.car_rental_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springproject.car_rental_spring.dto.AuthenticationRequest;
import com.springproject.car_rental_spring.dto.AuthenticationResponse;
import com.springproject.car_rental_spring.dto.SignupRequest;
import com.springproject.car_rental_spring.dto.UserDTO;
import com.springproject.car_rental_spring.repository.UserRepository;
import com.springproject.car_rental_spring.services.auth.AuthService;
import com.springproject.car_rental_spring.services.jwt.UserService;
import com.springproject.car_rental_spring.utils.JWTUtil;
import com.springproject.car_rental_spring.entity.User;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.customerExistswithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exists with this email!", HttpStatus.NOT_ACCEPTABLE);
        UserDTO createCustomerDTO = authService.createCustomer(signupRequest);
        if (createCustomerDTO == null)
            return new ResponseEntity<>("Customer creation unsuccessful! Try again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createCustomerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        }
        final UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }
}
