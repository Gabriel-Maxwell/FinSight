package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class MonthlyPlan {
    private Long id;
    private User owner;
    private int month;
    private int year;
    private boolean closed;
    private Date creationDate;
    private Date lastUpdateDate;
    private BigDecimal totalBudget;
    private String clojureDescription;
    private BigDecimal amount;
    private List<User> collaborators;
    private List<Category> categories;
    private List<Transaction> transactions;
    private List<MonthClosure> closures;
    private List<FixedExpense> fixedExpenses;
    private List<ExtraIncome> extraIncomes;

    public BigDecimal totalExtraIncome() {
        return this.extraIncomes.stream()
                .map(ExtraIncome::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
