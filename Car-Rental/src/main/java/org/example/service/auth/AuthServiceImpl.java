package org.example.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.entity.UserEntity;
import org.example.enums.UserRole;
import org.example.model.BookACarDto;
import org.example.model.SignupRequestDto;
import org.example.model.UserDto;
import org.example.repository.BookACarRepository;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService {
    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequestDto signupRequestDto) {
        UserEntity user=new UserEntity();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        UserEntity createdUser = userRepository.save(user);
        UserDto userDto=new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email)!=null;
    }

    @Override
    public UserEntity findFirstByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
