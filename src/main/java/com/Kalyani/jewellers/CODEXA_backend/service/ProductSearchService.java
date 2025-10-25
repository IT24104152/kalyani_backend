package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.PagedResponse;
import com.Kalyani.jewellers.CODEXA_backend.dto.ProductDTO;
import com.Kalyani.jewellers.CODEXA_backend.dto.ProductSearchRequest;
import com.Kalyani.jewellers.CODEXA_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchService {
    
    private final ProductRepository productRepository;
    
    public PagedResponse<ProductDTO> searchProducts(ProductSearchRequest searchRequest) {
        // Set default values if not provided
        if (searchRequest.getPage() == null) searchRequest.setPage(0);
        if (searchRequest.getPageSize() == null) searchRequest.setPageSize(20);
        if (searchRequest.getSortBy() == null) searchRequest.setSortBy("name");
        if (searchRequest.getSortDirection() == null) searchRequest.setSortDirection("asc");
        
        // Create pageable with sorting
        Sort sort = createSort(searchRequest.getSortBy(), searchRequest.getSortDirection());
        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPageSize(), sort);
        
        // Perform search
        Page<com.Kalyani.jewellers.CODEXA_backend.model.Product> productPage;
        
        if (hasSearchCriteria(searchRequest)) {
            productPage = productRepository.searchProducts(
                searchRequest.getSearchTerm(),
                searchRequest.getCategoryIds(),
                searchRequest.getMetalIds(),
                searchRequest.getGemIds(),
                searchRequest.getHasGemstone(),
                searchRequest.getMinWeight(),
                searchRequest.getMaxWeight(),
                searchRequest.getSize(),
                pageable
            );
        } else {
            // If no search criteria, return all products
            productPage = productRepository.findAll(pageable);
        }
        
        // Convert to DTOs
        List<ProductDTO> productDTOs = productPage.getContent().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        
        return PagedResponse.of(
                productDTOs,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements()
        );
    }
    
    private boolean hasSearchCriteria(ProductSearchRequest request) {
        return request.getSearchTerm() != null && !request.getSearchTerm().trim().isEmpty() ||
               request.getCategoryIds() != null && !request.getCategoryIds().isEmpty() ||
               request.getMetalIds() != null && !request.getMetalIds().isEmpty() ||
               request.getGemIds() != null && !request.getGemIds().isEmpty() ||
               request.getHasGemstone() != null ||
               request.getMinWeight() != null ||
               request.getMaxWeight() != null ||
               request.getSize() != null && !request.getSize().trim().isEmpty();
    }
    
    private Sort createSort(String sortBy, String sortDirection) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "name";
        }
        
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.DESC;
        }
        
        // Map sort fields to entity fields
        String fieldName = mapSortField(sortBy);
        
        return Sort.by(direction, fieldName);
    }
    
    private String mapSortField(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "weight" -> "weight";
            case "price" -> "initialProductionCost";
            case "created_date", "createdDate" -> "productId"; // Using ID as proxy for creation order
            default -> "name";
        };
    }
}
