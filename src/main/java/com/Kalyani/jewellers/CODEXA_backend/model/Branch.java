package com.Kalyani.jewellers.CODEXA_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Integer branchId;

    @NotBlank(message = "Branch name cannot be blank")
    @Size(max = 100, message = "Branch name cannot exceed 100 characters")
    @Column(name = "branch_name", nullable = false, length = 100)
    private String branchName;

    @NotBlank(message = "Branch code cannot be blank")
    @Size(max = 50, message = "Branch code cannot exceed 50 characters")
    @Column(name = "branch_code", nullable = false, length = 50, unique = true)
    private String branchCode;

    @NotBlank(message = "Branch address cannot be blank")
    @Size(max = 225, message = "Branch address cannot exceed 225 characters")
    @Column(name = "branch_address", nullable = false, length = 225)
    private String branchAddress;

    @NotBlank(message = "Branch telephone cannot be blank")
    //@Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid telephone number format")
    @Column(name = "branch_telephone", nullable = false, length = 20)
    private String branchTelephone;

    @NotBlank(message = "Branch hours cannot be blank")
    @Size(max = 100, message = "Branch hours cannot exceed 100 characters")
    @Column(name = "branch_hours", nullable = false, length = 100)
    private String branchHours;
}