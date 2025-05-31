package com.SpendControl.maxwell.SpendControl.mapper;

import com.SpendControl.maxwell.SpendControl.domain.User;
import com.SpendControl.maxwell.SpendControl.dto.UserDto;
import com.SpendControl.maxwell.SpendControl.entity.UserEntity;

import java.util.Collections;
import java.util.List;

public abstract class UserMapper {
    public static User toDomain(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .salary(entity.getSalary())
                .build();
    }
    public static List<User> toDomain(List<UserEntity> entities) {
        if(entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(UserMapper::toDomain)
                .toList();

//        List<User> result = new ArrayList<>();
//        for(UserEntity userEntity: entities){
//            User user = toDomain(userEntity);
//            result.add(user);
//        }
//        return result;

    }

    public static UserEntity fromDomain(User user){
        return  UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .salary(user.getSalary())
                .build();
    }

    public static User toDomain(UserDto dto){
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .salary(dto.getSalary())
                .build();
    }

    public static UserDto fromDomainToDto(User user){
        return  UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .salary(user.getSalary())
                .build();
    }

    public static List<UserDto> fromDomainToDto(List<User> users) {
        if(users == null || users.isEmpty()) {
            return Collections.emptyList();
        }
        return users.stream()
                .map(UserMapper::fromDomainToDto)
                .toList();
    }
}
