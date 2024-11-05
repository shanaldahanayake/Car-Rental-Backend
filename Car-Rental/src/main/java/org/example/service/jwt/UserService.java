package org.example.service.jwt;

import org.example.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    String verify(UserEntity users);
}
