package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.MetalDTO;
import com.Kalyani.jewellers.CODEXA_backend.model.Metal;
import com.Kalyani.jewellers.CODEXA_backend.service.MetalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metals")
@RequiredArgsConstructor
public class    MetalController {

    private final MetalService service;

    // Get all Metals
    @GetMapping
    public ResponseEntity<List<MetalDTO>> getAllMetals() {
        List<MetalDTO> metals = service.getAllMetals();
        return new ResponseEntity<>(metals, HttpStatus.OK);
    }

    // Get Metal by id
    @GetMapping("/{id}")
    public ResponseEntity<MetalDTO> getMetalById(@PathVariable int id) {
        MetalDTO metal = service.getMetal(id);
        return new ResponseEntity<>(metal, HttpStatus.OK);
    }

    // Create Metal
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MetalDTO create(@RequestBody MetalDTO metalDTO) {
        if (metalDTO.getMetalType() == null || metalDTO.getMetalType().trim().isEmpty()) {
            throw new IllegalArgumentException("metalType is required (GOLD/SILVER/ROSE_GOLD)");
        }
        if (metalDTO.getMetalPurity() == null || metalDTO.getMetalPurity().trim().isEmpty()) {
            throw new IllegalArgumentException("metalPurity is required");
        }
        return service.create(metalDTO);
    }

    // Update Metal
    @PutMapping("/{id}")
    public ResponseEntity<MetalDTO> updateMetal(@PathVariable int id, @RequestBody MetalDTO metalDTO) {
        MetalDTO updated = service.updateMetal(id, metalDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMetalById(@PathVariable int id) {
        service.deleteMetalById(id);
        return new ResponseEntity<>("Metal deleted successfully", HttpStatus.OK);
    }

}
