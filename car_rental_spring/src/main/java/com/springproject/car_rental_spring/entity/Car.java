package com.springproject.car_rental_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

import com.springproject.car_rental_spring.dto.CarDTO;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDTO getCarDTO(){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(id);
        carDTO.setName(name);
        carDTO.setBrand(brand);
        carDTO.setColor(color);
        carDTO.setPrice(price);
        carDTO.setDescription(description);
        carDTO.setType(type);
        carDTO.setTransmission(transmission);
        carDTO.setYear(year);
        carDTO.setReturnedImage(image);
        return carDTO;
    }

}
