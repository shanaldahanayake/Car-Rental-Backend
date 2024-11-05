package org.example.service.jwt;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserEntity;
import org.example.model.UserPrincipal;
import org.example.repository.UserRepository;
import org.example.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final JWTService service;
    private final UserRepository userRepo;
    private final AuthenticationManager manager;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public UserEntity register(UserEntity users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }


    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }

    @Override
    public String verify(UserEntity users) {
        Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if(authentication.isAuthenticated()){
            return service.generateToken(users.getUsername());
        }
        return "Fail";
    }
}
