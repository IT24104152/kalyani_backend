package com.Kalyani.jewellers.CODEXA_backend.model;

import com.Kalyani.jewellers.CODEXA_backend.enums.CustomDesignStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "custom_design")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class    CustomDesign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_id")
    private Integer designId;

    @Column(name = "assigned_user_id")
    private Integer assignedUserId;               // optional FK → user.user_id

    @Column(name = "customer_fname", nullable = false, length = 100)
    private String customerFname;

    @Column(name = "customer_lname", nullable = false, length = 100)
    private String customerLname;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Column(name = "budget")
    private BigDecimal budget;                 // DECIMAL(12,2) NULL

    @Column(name = "image", nullable = false)
    private byte[] image;                      // URL string

    @Column(name = "ticket_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ticketDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CustomDesignStatus status;         // NEW|REVIEWED|IN_PROGRESS|QUOTED|CLOSED

    @Column(name = "preferred_metal_id")
    private Integer preferredMetalId;

    // optional FK → metal.metal_id
}
