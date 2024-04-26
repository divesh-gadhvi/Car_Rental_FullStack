package com.springproject.car_rental_spring.dto;

import com.springproject.car_rental_spring.enums.BookCarStatus;

import lombok.Data;
import java.util.Date;

@Data
public class BookACarDTO {

    private Long id;

    private Date fromDate;

    private Date toDate;
    
    private Long days;

    private Long price;
    
    private BookCarStatus bookCarStatus;

    private Long carId;

    private Long userId;
}
