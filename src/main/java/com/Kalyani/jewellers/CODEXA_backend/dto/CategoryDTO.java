package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    private String name;

    public static CategoryDTO fromEntity(Category category) {
        if (category == null) return null;
        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getCategoryName())
                .build();
    }

    public Category toEntity() {
        Category category = new Category();
        category.setCategoryId(this.categoryId);
        category.setCategoryName(this.name);
        return category;
    }
}
