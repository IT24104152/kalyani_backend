package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.model.CustomDesign;
import com.Kalyani.jewellers.CODEXA_backend.enums.CustomDesignStatus;
import com.Kalyani.jewellers.CODEXA_backend.repository.CustomDesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomDesignService {
    private final CustomDesignRepository repo;

    //get all custom design tickets
    public List<CustomDesign> findAll() { return repo.findAll(); }

    //create custom design tickets
    public CustomDesign create(CustomDesign design) {
        if (design.getStatus() == null) design.setStatus(CustomDesignStatus.NEW);
        return repo.save(design);
    }

    //update custom design tickets
    public CustomDesign update(Integer id, CustomDesign incoming) {
        return repo.findById(id).map(existing -> {
            incoming.setDesignId(existing.getDesignId());
            return repo.save(incoming);
        }).orElseThrow(() -> new IllegalArgumentException("CustomDesign not found: " + id));
    }

    //delete custom design tickets by id
    public void delete(int id) { repo.deleteById(id); }

    //get custom design ticket by id
    public CustomDesign get(int id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("CustomDesign not found: " + id));
    }
}
