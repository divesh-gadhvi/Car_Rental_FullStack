package com.springproject.car_rental_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.car_rental_spring.entity.BookACar;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar, Long>{

}
