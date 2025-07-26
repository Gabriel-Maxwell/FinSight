package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.FixedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedExpenseRepository extends JpaRepository<FixedExpenseEntity, Long> {
    
    
}
