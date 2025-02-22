package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.ProductDto;
import com.kamilglazer.Vendi.model.Category;
import com.kamilglazer.Vendi.model.Product;

public class ProductMapper extends BaseMapper{


    public static ProductDto toDto(Product product) {
        return BaseMapper.returnNullIfNull(product) == null ? null : ProductDto.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .retailPrice(product.getRetailPrice())
                .salePrice(product.getSalePrice())
                .discountPercent(product.getDiscountPercent())
                .quantity(product.getQuantity())
                .color(product.getColor())
                .images(product.getImages())
                .numRatings(product.getNumRatings())
                .categoryId(product.getCategory().getId())
                .createdAt(product.getCreatedAt())
                .sizes(product.getSizes())
                .reviews(product.getReviews())
                .build();
    }

    public static Product toEntity(ProductDto productDto, Category category) {
        return BaseMapper.returnNullIfNull(productDto) == null ? null : Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .retailPrice(productDto.getRetailPrice())
                .salePrice(productDto.getSalePrice())
                .discountPercent(productDto.getDiscountPercent())
                .quantity(productDto.getQuantity())
                .color(productDto.getColor())
                .images(productDto.getImages())
                .numRatings(productDto.getNumRatings())
                .category(category)
                .createdAt(productDto.getCreatedAt())
                .sizes(productDto.getSizes())
                .reviews(productDto.getReviews())
                .build();
    }
}