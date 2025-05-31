package com.SpendControl.maxwell.SpendControl.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private BigDecimal salary;
}
