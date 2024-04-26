package com.springproject.car_rental_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.car_rental_spring.services.customer.CustomerService;
import com.springproject.car_rental_spring.dto.BookACarDTO;
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

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody BookACarDTO bookACarDTO){
        boolean success = customerService.bookACar(bookACarDTO);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId){
        CarDTO carDTO = customerService.getCarbyId(carId);
        if(carDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carDTO);
    }
}
