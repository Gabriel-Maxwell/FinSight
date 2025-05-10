package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FixedExpense {
    private Long id;
    private String description;
    private BigDecimal amount;
    private User user;
}
