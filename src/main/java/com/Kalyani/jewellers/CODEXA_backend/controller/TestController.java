package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.repository.ProductRepository;
import com.Kalyani.jewellers.CODEXA_backend.repository.CategoryRepository;
import com.Kalyani.jewellers.CODEXA_backend.repository.MetalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final MetalRepository metalRepo;

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Backend is running!");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }

    @GetMapping("/db-status")
    public Map<String, Object> databaseStatus() {
        Map<String, Object> response = new HashMap<>();
        try {
            long productCount = productRepo.count();
            long categoryCount = categoryRepo.count();
            long metalCount = metalRepo.count();
            
            response.put("status", "CONNECTED");
            response.put("database", "jewellery");
            response.put("productCount", productCount);
            response.put("categoryCount", categoryCount);
            response.put("metalCount", metalCount);
            response.put("message", "Database connection successful!");
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", "Database connection failed: " + e.getMessage());
            response.put("error", e.getClass().getSimpleName());
        }
        return response;
    }
}
