package com.springproject.car_rental_spring.services.customer;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.springproject.car_rental_spring.entity.BookACar;
import com.springproject.car_rental_spring.entity.Car;
import com.springproject.car_rental_spring.entity.User;
import com.springproject.car_rental_spring.enums.BookCarStatus;
import com.springproject.car_rental_spring.dto.BookACarDTO;
import com.springproject.car_rental_spring.dto.CarDTO;
import com.springproject.car_rental_spring.repository.BookACarRepository;
import com.springproject.car_rental_spring.repository.CarRepository;
import com.springproject.car_rental_spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final BookACarRepository bookACarRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDTO bookACarDTO) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDTO.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDTO.getUserId());
        if(optionalCar.isPresent() && optionalUser.isPresent()){
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMilliSeconds = bookACarDTO.getToDate().getTime() - bookACarDTO.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
            bookACar.setDays(days);
            bookACar.setPrice(existingCar.getPrice() * days);
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }
}
