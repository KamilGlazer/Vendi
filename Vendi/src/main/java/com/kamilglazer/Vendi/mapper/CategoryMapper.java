package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.CategoryDto;
import com.kamilglazer.Vendi.model.Category;

public class CategoryMapper {

    private static <T> T returnNullIfNull(T object){
        return object;
    }

    public static CategoryDto toDto(Category category) {
        return returnNullIfNull(category) == null ? null : CategoryDto.builder()
                .name(category.getName())
                .categoryId(category.getCategoryId())
                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .level(category.getLevel())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto, Category parentCategory) {
        return returnNullIfNull(categoryDto) == null ? null : Category.builder()
                .name(categoryDto.getName())
                .categoryId(categoryDto.getCategoryId())
                .parentCategory(parentCategory)
                .level(categoryDto.getLevel())
                .build();
    }
}
