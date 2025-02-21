package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.dto.CategoryDto;
import com.kamilglazer.Vendi.exception.CategoryExistsException;
import com.kamilglazer.Vendi.exception.CategoryNotFoundException;
import com.kamilglazer.Vendi.mapper.CategoryMapper;
import com.kamilglazer.Vendi.model.Category;
import com.kamilglazer.Vendi.repository.CategoryRepository;
import com.kamilglazer.Vendi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryRepository.findByName(categoryDto.getName()).ifPresent(category -> {
                    throw new CategoryExistsException("Category with name: " + categoryDto.getName() + " already exists");
                });

        Category parentCategory = null;
        if(categoryDto.getParentCategoryId() != null){
            parentCategory =  categoryRepository.findById(categoryDto.getParentCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Parent category not found"));
        }

        Category category = CategoryMapper.toEntity(categoryDto,parentCategory);
        category = categoryRepository.save(Objects.requireNonNull(category));
        return CategoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> findCategoriesByLevel(Integer level) {
        List<Category> categories = categoryRepository.findAllByLevel(level);
        return categories.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        return CategoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found")));
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto updateCategory(Long id,CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        category.setName(categoryDto.getName());
        category.setLevel(categoryDto.getLevel());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> findCategoriesByParentId(Long parentId) {
        Category category = categoryRepository.findById(parentId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        List<Category> categories = categoryRepository.findByParentCategory(category);
        return categories.stream().map(CategoryMapper::toDto).toList();
    }
}
