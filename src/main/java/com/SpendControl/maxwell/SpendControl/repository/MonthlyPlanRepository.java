package com.SpendControl.maxwell.SpendControl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpendControl.maxwell.SpendControl.entity.MonthlyPlanEntity;

public interface MonthlyPlanRepository extends JpaRepository<MonthlyPlanEntity,Long>{
    @Query("SELECT p FROM MonthlyPlanEntity p JOIN p.collaborators c WHERE (p.owner.id = :userId OR c.id = :userId) AND p.month >= :month AND p.year >= :year")
    List<MonthlyPlanEntity> findActivePlanByUserId(Long userId, int month, int year);
}
