package com.Kalyani.jewellers.CODEXA_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "customer_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="author_name", nullable=false, length=80)
    private String authorName;

    @Column(nullable=false, columnDefinition = "TINYINT UNSIGNED")
    private short rating; // maps to TINYINT

    @Column(nullable=false, columnDefinition="TEXT")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ReviewStatus status = ReviewStatus.PENDING;

    @Column(name="created_at", nullable=false, updatable=false, insertable=false,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Column(name="updated_at", nullable=false, insertable=false, updatable=false,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updatedAt;

    private Instant moderatedAt;
    private String moderatedBy;
    private String moderationNote;
    private Instant deletedAt;
    public boolean isDeleted() {
        return deletedAt != null;
    }
}
