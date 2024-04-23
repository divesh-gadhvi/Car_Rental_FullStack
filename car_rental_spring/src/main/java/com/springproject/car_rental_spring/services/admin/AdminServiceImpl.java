package com.springproject.car_rental_spring.services.admin;

import org.springframework.stereotype.Service;

import com.springproject.car_rental_spring.dto.CarDTO;
import com.springproject.car_rental_spring.entity.Car;
import com.springproject.car_rental_spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDTO carDTO) throws IOException {
        try {
            Car car = new Car();
            car.setName(carDTO.getName());
            car.setBrand(carDTO.getBrand());
            car.setColor(carDTO.getColor());
            car.setPrice(carDTO.getPrice());
            car.setYear(carDTO.getYear());
            car.setType(carDTO.getType());
            car.setDescription(carDTO.getDescription());
            car.setTransmission(carDTO.getTransmission());
            car.setImage(carDTO.getImage().getBytes());
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
}
