package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.dto.ProductDto;
import com.kamilglazer.Vendi.exception.CategoryNotFoundException;
import com.kamilglazer.Vendi.exception.ProductNotFoundException;
import com.kamilglazer.Vendi.mapper.ProductMapper;
import com.kamilglazer.Vendi.model.Category;
import com.kamilglazer.Vendi.model.Product;
import com.kamilglazer.Vendi.repository.CategoryRepository;
import com.kamilglazer.Vendi.repository.ProductRepository;
import com.kamilglazer.Vendi.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto product) {
        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        product.setNumRatings(0);
        product.setCreatedAt(LocalDateTime.now());
        return ProductMapper.toDto(productRepository.save(Objects.requireNonNull(ProductMapper.toEntity(product, category))));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductMapper.toDto(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto,Long productId) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setRetailPrice(productDto.getRetailPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setQuantity(productDto.getQuantity());
        product.setColor(productDto.getColor());
        product.setImages(productDto.getImages());
        product.setNumRatings(productDto.getNumRatings());
        product.setCategory(category);
        product.setSizes(productDto.getSizes());

        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> searchProduct(String query) {
        List<Product> products = productRepository.searchProduct(query);
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public Page<ProductDto> getAllProducts(Integer pageNumber, String sort, String category, String brand, String colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(category!=null){
                Join<Product,Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"),category));
            }
            if(colors !=null && !colors.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("color"),colors));
            }
            if(sizes !=null && !sizes.isEmpty()){
                predicates.add(root.get("sizes").in(sizes));
            }
            if(minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("salePrice"),minPrice));
            }
            if(maxPrice !=null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("salePrice"),maxPrice));
            }
            if(minDiscount !=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"),minDiscount));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Pageable pageable;
        if(sort!=null && !sort.isEmpty()){
            pageable = switch (sort) {
                case "price_low" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("salePrice").ascending());
                case "price_high" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("salePrice").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
            };
        }else{
            pageable=PageRequest.of(pageNumber!=null ? pageNumber : 0, 10, Sort.unsorted());
        }
        return productRepository.findAll(specification,pageable).map(ProductMapper::toDto);
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        return products.stream().map(ProductMapper::toDto).toList();
    }
}
