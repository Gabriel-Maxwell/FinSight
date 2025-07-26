package com.SpendControl.maxwell.SpendControl.domain;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    private Long id;
    private Date date;
    private BigDecimal amount;
    private String description;
    private Category category;
    private MonthlyPlan monthlyPlan;
}
