package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.MonthlyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthlyPlanRepository extends JpaRepository<MonthlyPlanEntity,Long>{
    @Query("SELECT p FROM MonthlyPlan p JOIN p.collaborators c WHERE (p.owner.id = :userId OR c.id = :userId) AND p.month >= :month AND p.year >= :year")
    List<MonthlyPlanEntity> findActivePlanByUserId(Long userId, int month, int year);
}
