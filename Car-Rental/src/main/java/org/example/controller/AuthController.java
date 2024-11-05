package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserEntity;
import org.example.model.AuthenticationRequestDto;
import org.example.model.AuthenticationResponseDto;
import org.example.model.SignupRequestDto;
import org.example.model.UserDto;
import org.example.repository.UserRepository;
import org.example.service.auth.AuthService;
import org.example.service.jwt.UserService;
import org.example.service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTService jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.hasCustomerWithEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto=authService.createCustomer(signupRequestDto);
        if(userDto==null){
            return new ResponseEntity<>("Customer Not Created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    @PostMapping("/log")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody UserEntity entity){
        AuthenticationResponseDto responseDto=new AuthenticationResponseDto();
        UserEntity firstByEmail = authService.findFirstByEmail(entity.getEmail());

        responseDto.setJwt(userService.verify(entity));
        responseDto.setUserId(firstByEmail.getId());
        responseDto.setUserRole(firstByEmail.getUserRole());
        return responseDto;
    }
}
