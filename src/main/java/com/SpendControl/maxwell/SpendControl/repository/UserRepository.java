package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    

    List<UserEntity> findByNameContainingIgnoreCase(String name);
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
