package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.GemDTO;
import com.Kalyani.jewellers.CODEXA_backend.exception.ResourceNotFoundException;
import com.Kalyani.jewellers.CODEXA_backend.model.Gem;
import com.Kalyani.jewellers.CODEXA_backend.repository.GemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GemService {
    private final GemRepository repo;

    public List<GemDTO> getAllGems() {
        return repo.findAll().stream()
                .map(GemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public GemDTO getGem(Integer id) {
        Gem gem = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gem", "id", id));
        return GemDTO.fromEntity(gem);
    }

    public Gem getGemEntity(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gem", "id", id));
    }

    @Transactional
    public Gem create(String gemName, String karatRate, MultipartFile imageFile) throws IOException {
        Gem g = new Gem();
        g.setGemName(gemName);
        if (karatRate != null && !karatRate.isBlank()) {
            g.setKaratRate(new BigDecimal(karatRate));
        }
        if (imageFile != null && !imageFile.isEmpty()) {
            g.setImageFileName(imageFile.getOriginalFilename());
            g.setImageContentType(imageFile.getContentType());
            g.setImageFileSize(imageFile.getSize());
            g.setImageData(imageFile.getBytes());
        }
        return repo.save(g);
    }

    @Transactional
    public GemDTO updateGem(int id, String gemName, String karatRate, MultipartFile imageFile) throws IOException {
        Gem existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gem", "id", id));
        
        existing.setGemName(gemName);
        if (karatRate != null && !karatRate.isBlank()) {
            existing.setKaratRate(new BigDecimal(karatRate));
        }
        
        if (imageFile != null && !imageFile.isEmpty()) {
            existing.setImageFileName(imageFile.getOriginalFilename());
            existing.setImageContentType(imageFile.getContentType());
            existing.setImageFileSize(imageFile.getSize());
            existing.setImageData(imageFile.getBytes());
        } else if (imageFile != null && imageFile.isEmpty()) {
            // If an empty file is sent, it might mean to clear the image
            existing.setImageData(null);
            existing.setImageFileName(null);
            existing.setImageContentType(null);
            existing.setImageFileSize(null);
        }

        Gem updated = repo.save(existing);
        return GemDTO.fromEntity(updated);
    }

    public void deleteGem(int id) {
        Gem gem = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gem", "id", id));
        repo.delete(gem);
    }



}

