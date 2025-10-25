package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.model.CustomDesign;
import com.Kalyani.jewellers.CODEXA_backend.service.CustomDesignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customdesign")
@RequiredArgsConstructor
public class CustomDesignController {

    private final CustomDesignService service;

    /**
     * Retrieves all custom designs from the system.
     * @return List of all CustomDesign objects
     */
    @GetMapping("/designs")
    public List<CustomDesign> list() {
        return service.findAll();
    }

    /**
     * Retrieves a specific custom design by its ID.
     * @param id ID of the custom design to fetch
     * @return CustomDesign object
     */
    @GetMapping("{id}")
    public CustomDesign get(@PathVariable int id) {
        return service.get(id);
    }

    /**
     * Creates a new custom design.
     * @param customDesign CustomDesign object containing the design details
     * @return Newly created CustomDesign object
     */
    @PostMapping("/create")
    public CustomDesign create(@RequestBody CustomDesign customDesign) {
        return service.create(customDesign);
    }

    /**
     * Updates an existing custom design by its ID.
     * @param id ID of the design to update
     * @param customDesign CustomDesign object containing updated details
     * @return Updated CustomDesign object
     */
    @PutMapping("/{id}")
    public CustomDesign update(@PathVariable Integer id, @RequestBody CustomDesign customDesign) {
        return service.update(id, customDesign);
    }

    /**
     * Deletes a custom design by its ID.
     * @param id ID of the custom design to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
