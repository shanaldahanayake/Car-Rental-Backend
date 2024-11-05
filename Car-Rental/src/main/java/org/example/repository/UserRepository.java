package org.example.repository;

import org.example.entity.UserEntity;
import org.example.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findFirstByEmail(String email);

    UserEntity findFirstByUserRole(UserRole userRole);
}
