package com.kamilglazer.Vendi.controller;

import com.kamilglazer.Vendi.dto.CategoryDto;
import com.kamilglazer.Vendi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByLevel(@PathVariable Integer level){
        return ResponseEntity.ok(categoryService.findCategoriesByLevel(level));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByParentId(@PathVariable Long parentId){
        return ResponseEntity.ok(categoryService.findCategoriesByParentId(parentId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
