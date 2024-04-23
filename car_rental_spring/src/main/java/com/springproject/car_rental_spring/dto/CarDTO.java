package com.springproject.car_rental_spring.dto;

import lombok.Data;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Data
public class CarDTO {
    private Long id;

    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;
    private MultipartFile image;

    private byte[] returnedImage;
}
