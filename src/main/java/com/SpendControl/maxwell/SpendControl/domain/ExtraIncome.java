package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExtraIncome {
    private Long id;
    private String description;
    private String source;
    private BigDecimal amount;
    private MonthlyPlan monthlyPlan;
    

}
