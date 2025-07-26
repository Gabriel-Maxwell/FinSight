package com.SpendControl.maxwell.SpendControl.mapper;

import com.SpendControl.maxwell.SpendControl.domain.Category;
import com.SpendControl.maxwell.SpendControl.dto.CategoryDto;
import com.SpendControl.maxwell.SpendControl.entity.CategoryEntity;

import java.util.Collections;
import java.util.List;

public abstract class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .goal(entity.getGoal())
                .spent(entity.getSpent())
                .build();
    }

    public static List<Category> toDomain(List<CategoryEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }

    public static CategoryEntity fromDomain(Category category) {
        if (category == null) return null;
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .type(category.getType())
                .goal(category.getGoal())
                .spent(category.getSpent())
                .build();
    }

    public static CategoryDto fromDomainToDto(Category category) {
        if (category == null) return null;
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .type(category.getType())
                .goal(category.getGoal())
                .spent(category.getSpent())
                .build();
    }
    public static Category toDomain(CategoryDto dto) {
        if (dto == null) return null;
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .type(dto.getType())
                .goal(dto.getGoal())
                .spent(dto.getSpent())
                .build();
    }
    public static List<CategoryDto> fromDomainToDto(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return Collections.emptyList();
        }
        return categories.stream()
                .map(CategoryMapper::fromDomainToDto)
                .toList();
    }
}
