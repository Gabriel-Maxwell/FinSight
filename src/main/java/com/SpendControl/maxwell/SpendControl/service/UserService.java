package com.SpendControl.maxwell.SpendControl.service;

import com.SpendControl.maxwell.SpendControl.domain.User;
import com.SpendControl.maxwell.SpendControl.dto.UserDto;
import com.SpendControl.maxwell.SpendControl.entity.UserEntity;
import com.SpendControl.maxwell.SpendControl.mapper.UserMapper;
import com.SpendControl.maxwell.SpendControl.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDto createUser(UserDto user) {
        validateUser(user);
        UserEntity entity = UserMapper.fromDomain(UserMapper.toDomain(user));
        entity = userRepository.save(entity);
        return UserMapper.fromDomainToDto(UserMapper.toDomain(entity));
    }

    public List<UserDto> findUsers(String name) {
        if(name == null || name.isBlank()){
           return UserMapper.fromDomainToDto(UserMapper.toDomain(userRepository.findAll()));
        }

        return UserMapper.fromDomainToDto(UserMapper.toDomain(userRepository.findByNameContainingIgnoreCase(name)));
    }

    public UserDto findUserByID(Long id){
        if (id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + id));
        return UserMapper.fromDomainToDto(UserMapper.toDomain(user));
    }

    private void validateUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User password cannot be null or empty");
        }
        if (user.getSalary() == null || user.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("User salary cannot be null or less than or equal to zero");
        }
        if(userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()){
            //todo elaborar mensagem padrao de erros
            throw new IllegalArgumentException("This email is already taken");
        }
    }

    @Transactional
    public User updateSalary(Long userId, BigDecimal newSalary) {
        if (newSalary == null || newSalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salary must be a positive number.");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Update salary in User (useful for new plans going forward)
        user.setSalary(newSalary);
        userRepository.save(user);

        return UserMapper.toDomain(user);
    }
}
