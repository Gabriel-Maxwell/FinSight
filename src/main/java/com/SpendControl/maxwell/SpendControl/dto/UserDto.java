package com.SpendControl.maxwell.SpendControl.dto;

import java.math.BigDecimal;

import com.SpendControl.maxwell.SpendControl.enums.UserProfile;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private BigDecimal salary;
    private UserProfile profile;
}
