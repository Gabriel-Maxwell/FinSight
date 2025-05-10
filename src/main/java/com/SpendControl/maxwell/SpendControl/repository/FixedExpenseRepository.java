package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.FixedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedExpenseRepository extends JpaRepository<FixedExpenseEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find fixed expenses by user:
    // List<FixedExpense> findByUser(User user);
    
}
