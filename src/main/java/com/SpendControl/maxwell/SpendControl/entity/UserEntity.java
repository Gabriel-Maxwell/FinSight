package com.SpendControl.maxwell.SpendControl.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private BigDecimal salary;
    @OneToMany(mappedBy = "user")
    private List<MonthlyPlanEntity>  ownedPlans;
    @OneToMany(mappedBy = "user")
    private List<FixedExpenseEntity> fixedExpenses;

}
