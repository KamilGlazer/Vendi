package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto product,Long productId);
    void deleteProductById(Long id);
    List<ProductDto> searchProduct(String query);
    Page<ProductDto> getAllProducts(Integer pageNumber, String sort, String category,String brand,String colors,List<String> sizes,Integer minPrice,Integer maxPrice,
                                    Integer minDiscount);
    List<ProductDto> getProductsByCategory(Long categoryId);
}
