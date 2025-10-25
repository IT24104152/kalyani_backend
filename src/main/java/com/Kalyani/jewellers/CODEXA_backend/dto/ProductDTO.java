package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.model.Category;
import com.Kalyani.jewellers.CODEXA_backend.model.Gem;
import com.Kalyani.jewellers.CODEXA_backend.model.Metal;
import com.Kalyani.jewellers.CODEXA_backend.model.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String name;
    private String size;
    private BigDecimal weight;
    private boolean hasGemstone;
    private BigDecimal initialProductionCost;
    private Integer quantity;
    private String productDescription;
    
    // Nested DTOs
    private CategoryDTO category;
    private MetalDTO metal;
    private Set<GemDTO> gems;
    private List<ProductImageDTO> images;

    // Static factory method to convert from Entity to DTO
    public static ProductDTO fromEntity(com.Kalyani.jewellers.CODEXA_backend.model.Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .size(product.getSize())
                .weight(product.getWeight())
                .hasGemstone(product.isHasGemstone())
                .initialProductionCost(product.getInitialProductionCost())
                .quantity(product.getQuantity())
                .productDescription(product.getProductDescription())
                .category(product.getCategory() != null ? CategoryDTO.fromEntity(product.getCategory()) : null)
                .metal(product.getMetal() != null ? MetalDTO.fromEntity(product.getMetal()) : null)
                .gems(product.getGems() != null ? 
                    product.getGems().stream()
                        .map(GemDTO::fromEntity)
                        .collect(Collectors.toSet()) : null)
                .images(product.getImages() != null ?
                    product.getImages().stream()
                        .map(ProductImageDTO::fromEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    // Convert DTO to Entity (for creating/updating)
    public com.Kalyani.jewellers.CODEXA_backend.model.Product toEntity() {
        com.Kalyani.jewellers.CODEXA_backend.model.Product product = new com.Kalyani.jewellers.CODEXA_backend.model.Product();
        product.setProductId(this.productId);
        product.setName(this.name);
        product.setSize(this.size);
        product.setWeight(this.weight);
        product.setHasGemstone(this.hasGemstone);
        product.setInitialProductionCost(this.initialProductionCost);
        product.setQuantity(this.quantity);
        product.setProductDescription(this.productDescription);
        
        if (this.category != null) {
            product.setCategory(this.category.toEntity());
        }
        if (this.metal != null) {
            product.setMetal(this.metal.toEntity());
        }
        if (this.gems != null) {
            product.setGems(this.gems.stream()
                .map(GemDTO::toEntity)
                .collect(Collectors.toSet()));
        }
        if (this.images != null) {
            product.setImages(this.images.stream()
                .map(ProductImageDTO::toEntity)
                .collect(Collectors.toList()));
        }
        
        return product;
    }
}

