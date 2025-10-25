package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // Search products by name (case insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Product> findByNameContainingIgnoreCase(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    // Search products by multiple criteria
    @Query("SELECT DISTINCT p FROM Product p " +
           "LEFT JOIN p.category c " +
           "LEFT JOIN p.metal m " +
           "LEFT JOIN p.gems g " +
           "WHERE (:searchTerm IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "AND (:categoryIds IS NULL OR c.categoryId IN :categoryIds) " +
           "AND (:metalIds IS NULL OR m.metalId IN :metalIds) " +
           "AND (:gemIds IS NULL OR g.gemId IN :gemIds) " +
           "AND (:hasGemstone IS NULL OR p.hasGemstone = :hasGemstone) " +
           "AND (:minWeight IS NULL OR p.weight >= :minWeight) " +
           "AND (:maxWeight IS NULL OR p.weight <= :maxWeight) " +
           "AND (:size IS NULL OR p.size = :size)")
    Page<Product> searchProducts(@Param("searchTerm") String searchTerm,
                                @Param("categoryIds") List<Integer> categoryIds,
                                @Param("metalIds") List<Integer> metalIds,
                                @Param("gemIds") List<Integer> gemIds,
                                @Param("hasGemstone") Boolean hasGemstone,
                                @Param("minWeight") BigDecimal minWeight,
                                @Param("maxWeight") BigDecimal maxWeight,
                                @Param("size") String size,
                                Pageable pageable);
    
    // Find products by category
    Page<Product> findByCategoryCategoryId(Integer categoryId, Pageable pageable);
    
    // Find products by metal
    Page<Product> findByMetalMetalId(Integer metalId, Pageable pageable);
    
    // Find products with gemstones
    Page<Product> findByHasGemstoneTrue(Pageable pageable);
    
    // Find products without gemstones
    Page<Product> findByHasGemstoneFalse(Pageable pageable);
    
    // Find products by weight range
    Page<Product> findByWeightBetween(BigDecimal minWeight, BigDecimal maxWeight, Pageable pageable);
    
    // Find products by size
    Page<Product> findBySize(String size, Pageable pageable);
    
    // Find products by gem
    @Query("SELECT DISTINCT p FROM Product p JOIN p.gems g WHERE g.gemId = :gemId")
    Page<Product> findByGemId(@Param("gemId") Integer gemId, Pageable pageable);
}

