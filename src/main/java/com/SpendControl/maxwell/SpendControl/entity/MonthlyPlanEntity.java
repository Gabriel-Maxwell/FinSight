package com.SpendControl.maxwell.SpendControl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name="monthly_plan")
public class MonthlyPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity owner;
    // @Column(name = "plan_month")  Para H2
    private int month;
    // @Column(name = "plan_year")  Para H2
    private int year;
    private boolean closed;
    private Date creationDate;
    private Date lastUpdateDate;
    private BigDecimal totalBudget;
    private String clojureDescription;
    private BigDecimal amount;
    @ManyToMany
    @JoinTable(
            name = "MonthlyPlan_Collaborator",
            joinColumns = @JoinColumn(name = "monthlyplan_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> collaborators;
    @ManyToMany
    @JoinTable(
        name = "MonthlyPlan_Category",
        joinColumns = @JoinColumn(name = "monthlyplan_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;
    @OneToMany(mappedBy = "monthlyPlan")
    private List<TransactionEntity> transactions;
    @OneToMany(mappedBy = "monthlyPlan")
    private List<MonthClosureEntity> closures;
    @ManyToMany
    @JoinTable(
        name = "MonthlyPlan_FixedExpense",
        joinColumns = @JoinColumn(name = "monthlyplan_id"),
        inverseJoinColumns = @JoinColumn(name = "fixedexpense_id")
    )
    private List<FixedExpenseEntity> fixedExpenses;
    @OneToMany(mappedBy = "monthlyPlan")
    private List<ExtraIncomeEntity> extraIncomes;

    public BigDecimal totalExtraIncome() {
        return this.extraIncomes.stream()
                .map(ExtraIncomeEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
