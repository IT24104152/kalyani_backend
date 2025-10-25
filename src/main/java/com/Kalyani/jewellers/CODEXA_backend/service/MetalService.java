package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.MetalDTO;
import com.Kalyani.jewellers.CODEXA_backend.exception.ResourceNotFoundException;
import com.Kalyani.jewellers.CODEXA_backend.model.Metal;
import com.Kalyani.jewellers.CODEXA_backend.repository.MetalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetalService {
    private final MetalRepository repo;

    public List<MetalDTO> getAllMetals(){
        return repo.findAll().stream()
                .map(MetalDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public MetalDTO create(MetalDTO metalDTO) {
        Metal metal = metalDTO.toEntity();
        Metal saved = repo.save(metal);
        return MetalDTO.fromEntity(saved);
    }

    public MetalDTO getMetal(int id) {
        Metal metal = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metal", "id", id));
        return MetalDTO.fromEntity(metal);
    }

    @Transactional
    public MetalDTO updateMetal(int id, MetalDTO metalDTO) {
        Metal existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metal", "id", id));
        
        existing.setMetalType(Metal.MetalType.valueOf(metalDTO.getMetalType()));
        existing.setMetalPurity(metalDTO.getMetalPurity());
        
        Metal updated = repo.save(existing);
        return MetalDTO.fromEntity(updated);
    }

    public void deleteMetalById(int id) {
        Metal metal = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metal", "id", id));
        repo.delete(metal);
    }


}

