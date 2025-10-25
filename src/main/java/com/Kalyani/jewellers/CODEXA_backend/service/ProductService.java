package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.*;
import com.Kalyani.jewellers.CODEXA_backend.exception.ResourceNotFoundException;
import com.Kalyani.jewellers.CODEXA_backend.model.*;
import com.Kalyani.jewellers.CODEXA_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final MetalRepository metalRepo;
    private final GemRepository gemRepo;
    private final ProductImageRepository imageRepo;

    @Transactional
    public Product create(Map<String, String> fields, List<MultipartFile> images) throws IOException {

        Integer categoryId = Integer.valueOf(required(fields, "categoryId"));
        Integer metalId    = Integer.valueOf(required(fields, "metalId"));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Metal metal = metalRepo.findById(metalId)
                .orElseThrow(() -> new ResourceNotFoundException("Metal", "id", metalId));

        Product p = new Product();
        p.setCategory(category);
        p.setMetal(metal);

        p.setName(required(fields, "name"));
        p.setSize(fields.getOrDefault("size", null));
        p.setWeight(new BigDecimal(required(fields, "weight")));
        p.setInitialProductionCost(new BigDecimal(required(fields, "initialProductionCost")));
        p.setQuantity(Integer.valueOf(required(fields, "quantity")));
        p.setProductDescription(required(fields, "productDescription"));
        
        // Handle hasGemstone field
        String hasGemstoneStr = fields.getOrDefault("hasGemstone", "false");
        p.setHasGemstone(Boolean.parseBoolean(hasGemstoneStr));

        // Gems (CSV)
        String gemIds = fields.get("gemIds");
        if (gemIds != null && !gemIds.isBlank()) {
            Set<Integer> ids = Arrays.stream(gemIds.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());
            if (!ids.isEmpty()) {
                Set<Gem> gems = new HashSet<>(gemRepo.findAllById(ids));
                p.setGems(gems);
                p.setHasGemstone(true);
            }
        } else {
            p.setHasGemstone(false);
        }

        p = productRepo.save(p);

        if (images != null) {
            for (MultipartFile file : images) {
                if (file == null || file.isEmpty()) continue;
                ProductImage img = new ProductImage();
                img.setProduct(p);
                img.setFileName(file.getOriginalFilename());
                img.setContentType(file.getContentType() != null ? file.getContentType() : MediaType.APPLICATION_OCTET_STREAM_VALUE);
                img.setFileSize(file.getSize());
                img.setData(file.getBytes());
                imageRepo.save(img);
                p.getImages().add(img);
            }
        }

        return p;
    }

//    public List<Product> list() {
//        return productRepo.findAll();
//    }

    public Product get(Integer id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    private static String required(Map<String,String> fields, String key) {
        String v = fields.get(key);
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException(key + " is required");
        }
        return v;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ProductDTO getProduct(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return ProductDTO.fromEntity(product);
    }

    @Transactional
    public Product update(int id, Map<String,String> fields, List<MultipartFile> newImages, String deleteImageIdsCsv) throws IOException {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        // Scalar updates (apply only if provided)
        if (fields.containsKey("name")) p.setName(req(fields, "name"));
        if (fields.containsKey("size")) p.setSize(opt(fields, "size"));
        if (fields.containsKey("weight")) p.setWeight(new BigDecimal(req(fields, "weight")));
                    if (fields.containsKey("initialProductionCost")) p.setInitialProductionCost(new BigDecimal(req(fields, "initialProductionCost")));
                    if (fields.containsKey("quantity")) p.setQuantity(Integer.valueOf(req(fields, "quantity")));
                    if (fields.containsKey("productDescription")) p.setProductDescription(req(fields, "productDescription"));
                    if (fields.containsKey("hasGemstone")) p.setHasGemstone(Boolean.parseBoolean(req(fields, "hasGemstone")));

        if (fields.containsKey("categoryId")) {
            Integer categoryId = Integer.valueOf(req(fields, "categoryId"));
            p.setCategory(categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)));
        }

        if (fields.containsKey("metalId")) {
            Integer metalId = Integer.valueOf(req(fields, "metalId"));
            p.setMetal(metalRepo.findById(metalId)
                    .orElseThrow(() -> new ResourceNotFoundException("Metal", "id", metalId)));
        }

        // Gems: if gemIds present, replace the set
        if (fields.containsKey("gemIds")) {
            Set<Gem> gems = parseGemIds(opt(fields, "gemIds"));
            p.getGems().clear();
            p.getGems().addAll(gems);
            p.setHasGemstone(!gems.isEmpty());
        }

        // Delete selected images
        if (deleteImageIdsCsv != null && !deleteImageIdsCsv.isBlank()) {
            Set<Integer> toDelete = Arrays.stream(deleteImageIdsCsv.split(","))
                    .map(String::trim).filter(s -> !s.isEmpty())
                    .map(Integer::valueOf).collect(Collectors.toSet());
            // Remove from collection (orphans will be deleted due to orphanRemoval)
            p.getImages().removeIf(img -> {
                if (img.getImageId() != null && toDelete.contains(img.getImageId())) {
                    imageRepo.deleteById(img.getImageId());
                    return true;
                }
                return false;
            });
        }

        // Add new images
        if (newImages != null) {
            for (MultipartFile file : newImages) {
                if (file == null || file.isEmpty()) continue;
                ProductImage img = new ProductImage();
                img.setProduct(p);
                img.setFileName(file.getOriginalFilename());
                img.setContentType(file.getContentType() != null ? file.getContentType() : MediaType.APPLICATION_OCTET_STREAM_VALUE);
                img.setFileSize(file.getSize());
                img.setData(file.getBytes());
                imageRepo.save(img);
                p.getImages().add(img);
            }
        }

        return p; // managed entity will be flushed
    }

    private Set<Gem> parseGemIds(String csv) {
        if (csv == null || csv.isBlank()) return new HashSet<>();
        Set<Integer> ids = Arrays.stream(csv.split(","))
                .map(String::trim).filter(s -> !s.isEmpty())
                .map(Integer::valueOf).collect(Collectors.toSet());
        if (ids.isEmpty()) return new HashSet<>();
        return new HashSet<>(gemRepo.findAllById(ids));
    }

    private static String req(Map<String,String> fields, String key) {
        String v = fields.get(key);
        if (v == null || v.isBlank()) throw new IllegalArgumentException(key + " is required");
        return v;
    }
    private static String opt(Map<String,String> fields, String key) { return fields.getOrDefault(key, null); }

    public void deleteProductById(Integer id) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepo.delete(p);
    }
}

