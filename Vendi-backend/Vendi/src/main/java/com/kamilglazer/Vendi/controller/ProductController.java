package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.dto.ProductDto;
import com.kamilglazer.Vendi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(productDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam(required = false) String search){
        return ResponseEntity.ok(productService.searchProduct(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(required = false) String category,
                                                           @RequestParam(required = false) String brand,
                                                           @RequestParam(required = false) String color,
                                                           @RequestParam(required = false) List<String> sizes,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice,
                                                           @RequestParam(required = false) Integer minDiscount,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false, defaultValue = "0") Integer pageNumber){
        return new ResponseEntity<>(productService.getAllProducts(pageNumber,sort,category,brand,color,sizes,minPrice,maxPrice,minDiscount),HttpStatus.OK);
    }

    @GetMapping("/getByCategory/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

}


