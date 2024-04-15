package com.springproject.car_rental_spring.services.auth;

import com.springproject.car_rental_spring.dto.SignupRequest;
import com.springproject.car_rental_spring.dto.UserDTO;

public interface AuthService {

    UserDTO createCustomer (SignupRequest signupRequest);

    boolean customerExistswithEmail(String email);
}
