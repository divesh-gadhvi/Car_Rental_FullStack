package com.springproject.car_rental_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.car_rental_spring.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
