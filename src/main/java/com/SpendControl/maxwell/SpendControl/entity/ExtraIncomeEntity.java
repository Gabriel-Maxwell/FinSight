package com.SpendControl.maxwell.SpendControl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ExtraIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String source;
    private BigDecimal amount;
    @ManyToOne
    private MonthlyPlanEntity monthlyPlan;
    

}
