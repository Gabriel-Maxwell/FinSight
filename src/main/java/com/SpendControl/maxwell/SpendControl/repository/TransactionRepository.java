package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.TransactionEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long>{
    
    List<TransactionEntity> findByDescriptionContainingIgnoreCase(String description);
    List<TransactionEntity> findByDateBetween(java.sql.Date startDate, java.sql.Date endDate);
    List<TransactionEntity> findByMonthlyPlanId(Long monthlyPlanId);

}
