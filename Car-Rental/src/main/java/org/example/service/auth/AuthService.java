package org.example.service.auth;

import org.example.entity.UserEntity;
import org.example.model.SignupRequestDto;
import org.example.model.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequestDto signupRequestDto);
    boolean hasCustomerWithEmail(String email);
    UserEntity findFirstByEmail(String email);
}
