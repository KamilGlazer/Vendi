package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE (:query IS NULL OR lower(p.title) LIKE lower(concat('%', :query, '%')) " +
            "OR (:query IS NULL OR lower(p.category.name) LIKE lower(concat('%', :query, '%'))))")
    List<Product> searchProduct(@Param("query") String query);

    List<Product> findAllByCategoryId(Long categoryId);

}
