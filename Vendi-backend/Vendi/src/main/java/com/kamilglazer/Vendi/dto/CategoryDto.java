package com.kamilglazer.Vendi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private String name;
    private String categoryId;
    private Long parentCategoryId;
    private Integer level;
}
