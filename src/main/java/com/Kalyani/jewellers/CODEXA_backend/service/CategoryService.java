package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.CategoryDTO;
import com.Kalyani.jewellers.CODEXA_backend.exception.ResourceNotFoundException;
import com.Kalyani.jewellers.CODEXA_backend.model.Category;
import com.Kalyani.jewellers.CODEXA_backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public List<CategoryDTO> getAllCategories() {
        return repo.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategory(String name) {
        Category category = repo.findByCategoryNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", name));
        return CategoryDTO.fromEntity(category);
    }

    public CategoryDTO getCategory(Integer id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return CategoryDTO.fromEntity(category);
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity();
        Category saved = repo.save(category);
        return CategoryDTO.fromEntity(saved);
    }

    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        
        existing.setCategoryName(categoryDTO.getName());
        Category updated = repo.save(existing);
        return CategoryDTO.fromEntity(updated);
    }

    public void deleteCategory(Integer id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        repo.delete(category);
    }






}

