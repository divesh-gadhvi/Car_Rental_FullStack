package com.springproject.car_rental_spring.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.car_rental_spring.dto.SignupRequest;
import com.springproject.car_rental_spring.dto.UserDTO;
import com.springproject.car_rental_spring.entity.User;
import com.springproject.car_rental_spring.enums.UserRole;
import com.springproject.car_rental_spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        return userDTO;
    }

    @Override
    public boolean customerExistswithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    } 

}
