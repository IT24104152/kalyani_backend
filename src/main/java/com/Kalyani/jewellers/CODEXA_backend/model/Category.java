package com.Kalyani.jewellers.CODEXA_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })  // Add these annotations so proxies/handlers are ignored and don’t stream BLOBs in list views.
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name", nullable = false, unique = true, length = 100)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}







//CREATE TABLE category (
//        category_id   INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//        category_name VARCHAR(100) NOT NULL UNIQUE,
//KEY idx_category_name (category_name)
//) ENGINE=InnoDB;