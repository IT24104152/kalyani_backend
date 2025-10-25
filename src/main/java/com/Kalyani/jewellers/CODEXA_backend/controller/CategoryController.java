package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.CategoryDTO;
import com.Kalyani.jewellers.CODEXA_backend.model.Category;
import com.Kalyani.jewellers.CODEXA_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    // Get all the Categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = service.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get Category by name
    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String name) {
        CategoryDTO category = service.getCategory(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Add new Categories
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saved = service.create(categoryDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update Category by id
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updated = service.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete Category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        service.deleteCategory(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}




