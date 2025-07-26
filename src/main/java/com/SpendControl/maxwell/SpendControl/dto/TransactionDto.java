package com.SpendControl.maxwell.SpendControl.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionDto {
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private Long categoryId;
    private String categoryName;
    private Long userId;
    private String userName;
}
