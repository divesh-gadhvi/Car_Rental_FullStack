package com.springproject.car_rental_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springproject.car_rental_spring.dto.SignupRequest;
import com.springproject.car_rental_spring.dto.UserDTO;
import com.springproject.car_rental_spring.services.auth.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")   
    public ResponseEntity<?> signupCustomer (@RequestBody SignupRequest signupRequest){
        if(authService.customerExistswithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exists with this email!", HttpStatus.NOT_ACCEPTABLE);
        UserDTO createCustomerDTO = authService.createCustomer(signupRequest);
        if (createCustomerDTO == null) return new ResponseEntity<>("Customer creation unsuccessful! Try again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createCustomerDTO, HttpStatus.CREATED);

    }
}
