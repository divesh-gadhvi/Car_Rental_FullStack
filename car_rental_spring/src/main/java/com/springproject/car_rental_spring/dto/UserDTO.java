package com.springproject.car_rental_spring.dto;
import com.springproject.car_rental_spring.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole userRole;

}
