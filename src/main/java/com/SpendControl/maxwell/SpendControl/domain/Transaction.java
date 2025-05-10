package com.SpendControl.maxwell.SpendControl.domain;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Transaction {
    private Long id;
    private Date date;
    private BigDecimal amount;
    private String description;
    private Category category;
    private MonthlyPlan monthlyPlan;
}
