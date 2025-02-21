package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
    List<CategoryDto> findCategoriesByLevel(Integer level);
    CategoryDto findCategoryById(Long id);
    List<CategoryDto> findAllCategories();
    CategoryDto updateCategory(Long id,CategoryDto categoryDto);
    List<CategoryDto> findCategoriesByParentId(Long parentId);
}
