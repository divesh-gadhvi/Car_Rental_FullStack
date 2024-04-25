package com.springproject.car_rental_spring.services.admin;

import org.springframework.stereotype.Service;

import com.springproject.car_rental_spring.dto.CarDTO;
import com.springproject.car_rental_spring.entity.Car;
import com.springproject.car_rental_spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDTO getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDTO).orElse(null);
    }

    @Override
    public boolean updateCar(Long id, CarDTO carDTO) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            Car existingCar = optionalCar.get();
            if(carDTO.getImage() != null) {
                existingCar.setImage(carDTO.getImage().getBytes());
            }
            existingCar.setName(carDTO.getName());
            existingCar.setBrand(carDTO.getBrand());
            existingCar.setColor(carDTO.getColor());
            existingCar.setPrice(carDTO.getPrice());
            existingCar.setYear(carDTO.getYear());
            existingCar.setType(carDTO.getType());
            existingCar.setDescription(carDTO.getDescription());
            existingCar.setTransmission(carDTO.getTransmission());
            carRepository.save(existingCar);
            return true;
        } else {
            return false;
        }
    }
}
