package com.SpendControl.maxwell.SpendControl.service;

import com.SpendControl.maxwell.SpendControl.dto.CategoryDto;
import com.SpendControl.maxwell.SpendControl.entity.CategoryEntity;
import com.SpendControl.maxwell.SpendControl.mapper.CategoryMapper;
import com.SpendControl.maxwell.SpendControl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        validateCategory(categoryDto);
        CategoryEntity entity = CategoryMapper.fromDomain(CategoryMapper.toDomain(categoryDto));
        entity = categoryRepository.save(entity);
        return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(entity));
    }


    public List<CategoryDto> findCategories(String name) {
        if (name == null || name.isBlank()) {
            return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(categoryRepository.findAll()));
        }
        return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(categoryRepository.findByNameContainingIgnoreCase(name)));
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        validateCategory(categoryDto);
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        entity.setName(categoryDto.getName());
        CategoryEntity updated = categoryRepository.save(entity);
        return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(updated));
    }

    public List<CategoryDto> findByNameContainingIgnoreCase(String name) {
        if (name == null || name.isBlank()) {
            return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(categoryRepository.findAll()));
        }
        return CategoryMapper.fromDomainToDto(CategoryMapper.toDomain(categoryRepository.findByNameContainingIgnoreCase(name)));
    }

    public void deleteCategory(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
    if (category.getTransactions() != null && !category.getTransactions().isEmpty()) {
        throw new IllegalStateException("is not possible to delete bound transactions.");
    }
    categoryRepository.deleteById(id);
}


    public void validateCategory(CategoryDto category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
      
        if (categoryRepository.existsByNameAndType(category.getName(), category.getType())) {
            throw new IllegalArgumentException("Category with the same name and type already exists");
        }
       
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Category description cannot be null or empty");
        }
    
        
    }
}
