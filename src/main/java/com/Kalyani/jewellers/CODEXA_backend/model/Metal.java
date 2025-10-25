package com.Kalyani.jewellers.CODEXA_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "metal", uniqueConstraints = @UniqueConstraint(name = "uq_metal_type_purity", columnNames = {"metal_type", "metal_purity"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Metal {

    public enum MetalType { GOLD, SILVER, ROSE_GOLD }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metal_id")
    private Integer metalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "metal_type", nullable = false, length = 20)
    private MetalType metalType;

    @Column(name = "metal_purity", nullable = false, length = 20)
    private String metalPurity;

    @OneToMany(mappedBy = "metal")
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

}







//CREATE TABLE metal (
//        metal_id     INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//        metal_type   ENUM('GOLD','SILVER','ROSE_GOLD') NOT NULL,
//        metal_purity VARCHAR(20) NOT NULL,
//UNIQUE KEY uq_metal_type_purity (metal_type, metal_purity),
//KEY idx_metal_type   (metal_type),
//KEY idx_metal_purity (metal_purity)
//) ENGINE=InnoDB;