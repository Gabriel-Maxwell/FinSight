package com.SpendControl.maxwell.SpendControl.repository;

import com.SpendControl.maxwell.SpendControl.entity.CategoryEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    List<CategoryEntity> findByNameContainingIgnoreCase(String name);

    boolean existsByNameAndType(String name, String type);
    
}
