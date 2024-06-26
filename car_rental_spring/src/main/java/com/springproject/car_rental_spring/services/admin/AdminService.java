package com.springproject.car_rental_spring.services.admin;

import java.io.IOException;
import java.util.List;

import com.springproject.car_rental_spring.dto.CarDTO;

public interface AdminService {
    
    boolean postCar(CarDTO carDTO) throws IOException;

    List<CarDTO> getAllCars();

    void deleteCar(Long id);

    CarDTO getCarById(Long id);

    boolean updateCar(Long id, CarDTO carDTO) throws IOException;
}
