package com.SpendControl.maxwell.SpendControl.mapper;

import com.SpendControl.maxwell.SpendControl.domain.User;
import com.SpendControl.maxwell.SpendControl.entity.UserEntity;

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

    public static UserEntity fromDomain(User user){
        return  UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .salary(user.getSalary())
                .build();
    }
}
