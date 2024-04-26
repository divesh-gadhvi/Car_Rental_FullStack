package com.springproject.car_rental_spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.car_rental_spring.services.customer.CustomerService;
import com.springproject.car_rental_spring.dto.CarDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        List<CarDTO> carDTOList = customerService.getAllCars();
        return ResponseEntity.ok(carDTOList);
    }
}
