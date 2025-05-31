package com.SpendControl.maxwell.SpendControl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name="transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private BigDecimal amount;
    private String description;
    @ManyToOne
    private CategoryEntity category;
    @ManyToOne
    private MonthlyPlanEntity monthlyPlan;
}
