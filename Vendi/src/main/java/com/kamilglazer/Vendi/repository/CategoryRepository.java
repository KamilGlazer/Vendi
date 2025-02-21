package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findAllByLevel(Integer level);
    List<Category> findByParentCategory(Category category);
}
