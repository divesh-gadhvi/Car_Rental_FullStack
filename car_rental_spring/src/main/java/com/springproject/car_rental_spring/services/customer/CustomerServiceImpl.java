package com.springproject.car_rental_spring.services.customer;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.springproject.car_rental_spring.entity.Car;
import com.springproject.car_rental_spring.dto.CarDTO;
import com.springproject.car_rental_spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CarRepository carRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }
}
