package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.*;
import com.Kalyani.jewellers.CODEXA_backend.model.Product;
import com.Kalyani.jewellers.CODEXA_backend.service.ProductService;
import com.Kalyani.jewellers.CODEXA_backend.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductSearchService productSearchService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<PagedResponse<ProductDTO>> searchProducts(@RequestBody ProductSearchRequest searchRequest) {
        PagedResponse<ProductDTO> response = productSearchService.searchProducts(searchRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(
            @RequestParam Map<String,String> fields,
            @RequestParam(name = "images", required = false) List<MultipartFile> images
    ) throws IOException {
        Product product = productService.create(fields, images);
        return ProductDTO.fromEntity(product);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO update(@PathVariable Integer id,
                          @RequestParam Map<String,String> fields,
                          @RequestParam(name = "images", required = false) List<MultipartFile> images,
                          @RequestParam(name = "deleteImageIds", required = false) String deleteImageIdsCsv) throws IOException {
        Product product = productService.update(id, fields, images, deleteImageIdsCsv);
        return ProductDTO.fromEntity(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}

