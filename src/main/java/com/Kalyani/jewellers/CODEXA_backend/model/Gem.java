package com.Kalyani.jewellers.CODEXA_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "gem")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Gem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gem_id")
    private Integer gemId;

    @Column(name = "gem_name", nullable = false)
    private String gemName;

    @Column(name = "image_file_name", length = 255)
    private String imageFileName;

    @Column(name = "image_content_type", length = 100)
    private String imageContentType;

    @Column(name = "image_file_size")
    private Long imageFileSize;    ///////////////////  BIGINT in table///

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] imageData;

    @Column(name = "image_created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime imageCreatedAt;

    @Column(name = "karat_rate", precision = 12, scale = 2)
    private BigDecimal karatRate;    ////  DECIMAL in table



    @ManyToMany(mappedBy = "gems")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}









//CREATE TABLE gem (
//        gem_id     INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//        gem_name   VARCHAR(100) NOT NULL,
//        image_file_name     VARCHAR(255)     NULL AFTER gem_name,
//        image_content_type  VARCHAR(100)     NULL AFTER image_file_name,
//        image_file_size     BIGINT UNSIGNED  NULL AFTER image_content_type,
//        image_data          LONGBLOB         NULL AFTER image_file_size,
//        image_created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER image_data;
//        karat_rate DECIMAL(12,2) NULL,
//KEY idx_gem_name (gem_name)
//) ENGINE=InnoDB;


//ALTER TABLE gem
//ADD COLUMN image_file_name     VARCHAR(255)     NULL AFTER gem_name,
//ADD COLUMN image_content_type  VARCHAR(100)     NULL AFTER image_file_name,
//ADD COLUMN image_file_size     BIGINT UNSIGNED  NULL AFTER image_content_type,
//ADD COLUMN image_data          LONGBLOB         NULL AFTER image_file_size,
//ADD COLUMN image_created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER image_data;







