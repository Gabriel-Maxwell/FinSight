package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.MonthClosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthClosureRepository extends JpaRepository<MonthClosureEntity,Long> {
    
}
