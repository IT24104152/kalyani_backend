package com.Kalyani.jewellers.CODEXA_backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.*;


@Entity
@Table(name = "product")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metal_id", nullable = false)
    private Metal metal;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "size", length = 50)
    private String size;

    @Column(name = "weight", precision = 8, scale = 2, nullable = false)
    private BigDecimal weight;

    @Column(name = "has_gemstone", nullable = false)
    private boolean hasGemstone = false;

    @Column(name = "initial_production_cost", precision = 12, scale = 2, nullable = false)
    private BigDecimal initialProductionCost;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    @ManyToMany
    @JoinTable(
            name = "product_gem",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "gem_id")
    )
    private Set<Gem> gems = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> images = new ArrayList<>();
}






//CREATE TABLE product (
//        product_id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//        category_id             INT UNSIGNED NOT NULL,
//        metal_id                INT UNSIGNED NOT NULL,
//        name                    VARCHAR(150) NOT NULL,
//size                    VARCHAR(50)  NULL,             -- size is optional
//weight                  DECIMAL(8,2) NOT NULL,         -- grams
//has_gemstone            BOOLEAN NOT NULL DEFAULT FALSE,
//initial_production_cost DECIMAL(12,2) NOT NULL,
//quantity                INT NOT NULL DEFAULT 0,
//product_description     TEXT NOT NULL,
//CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE RESTRICT ON UPDATE CASCADE,
//CONSTRAINT fk_product_metal    FOREIGN KEY (metal_id)    REFERENCES metal(metal_id)       ON DELETE RESTRICT ON UPDATE CASCADE,
//KEY idx_product_category (category_id),
//KEY idx_product_metal    (metal_id),
//KEY idx_product_gemstone   (has_gemstone),
//KEY idx_product_size       (size),
//KEY idx_product_weight     (weight),
//KEY idx_product_name       (name),
//KEY idx_prod_filter        (category_id, metal_id, has_gemstone)
//) ENGINE=InnoDB;