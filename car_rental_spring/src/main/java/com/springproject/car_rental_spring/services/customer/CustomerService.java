package com.springproject.car_rental_spring.services.customer;

import com.springproject.car_rental_spring.dto.BookACarDTO;
import com.springproject.car_rental_spring.dto.CarDTO;
import java.util.List;

public interface CustomerService {

    List<CarDTO> getAllCars();

    boolean bookACar(BookACarDTO bookACarDTO);
}
