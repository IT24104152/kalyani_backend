package com.Kalyani.jewellers.CODEXA_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequest {
    private String searchTerm;
    private List<Integer> categoryIds;
    private List<Integer> metalIds;
    private List<Integer> gemIds;
    private Boolean hasGemstone;
    private BigDecimal minWeight;
    private BigDecimal maxWeight;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String size;
    
    @Builder.Default
    private Integer page = 0;
    
    @Builder.Default
    private Integer pageSize = 20;
    
    @Builder.Default
    private String sortBy = "name"; // "name", "weight", "price", "created_date"
    
    @Builder.Default
    private String sortDirection = "asc"; // "asc", "desc"
}
