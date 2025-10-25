package com.Kalyani.jewellers.CODEXA_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "product_image")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", nullable = false, columnDefinition = "LONGBLOB")
    @JsonIgnore    //  << prevents Base64-ing the entire image in GET /products
    private byte[] data;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}






//CREATE TABLE product_image (
//        image_id   INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//        product_id INT UNSIGNED NOT NULL,
//        file_name     VARCHAR(255)     VARCHAR(255)    NOT NULL,,
//        content_type  VARCHAR(100)     VARCHAR(100)    NOT NULL,,
//        file_size     BIGINT UNSIGNED   NOT NULL,
//        data          LONGBLOB          NOT NULL,
//        is_primary    BOOLEAN NOT NULL DEFAULT FALSE,
//        alt_text      VARCHAR(255) NULL,
//        created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

//CONSTRAINT fk_image_product FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,

//KEY idx_image_product (product_id),
//KEY idx_product_primary (product_id, is_primary);
//) ENGINE=InnoDB;







//ALTER TABLE product_image
//DROP FOREIGN KEY fk_image_gem;
//
//-- Drop the gem_id index if it exists.
//DROP INDEX idx_image_gem ON product_image;
//
//-- Remove the gem_id column (images belong to products only).
//ALTER TABLE product_image
//DROP COLUMN gem_id;
//
//-- Add metadata + BLOB + primary flag (NULL first to be migration-safe).
//ALTER TABLE product_image
//ADD COLUMN file_name     VARCHAR(255)     NULL AFTER product_id,
//ADD COLUMN content_type  VARCHAR(100)     NULL AFTER file_name,
//ADD COLUMN file_size     BIGINT UNSIGNED  NULL AFTER content_type,
//ADD COLUMN data          LONGBLOB         NULL AFTER file_size,
//ADD COLUMN is_primary    BOOLEAN NOT NULL DEFAULT FALSE AFTER data,
//ADD COLUMN created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER alt_text;
//
//-- Fast lookup for (product_id, is_primary)
//CREATE INDEX idx_product_primary ON product_image (product_id, is_primary);
//
//-- We no longer store URLs in product_image
//ALTER TABLE product_image
//DROP COLUMN url;
//
//-- Tighten nullability (skip these 4 MODIFYs if you need to backfill first).
//ALTER TABLE product_image
//MODIFY file_name    VARCHAR(255)    NOT NULL,
//MODIFY content_type VARCHAR(100)    NOT NULL,
//MODIFY file_size    BIGINT UNSIGNED NOT NULL,
//MODIFY data         LONGBLOB        NOT NULL;