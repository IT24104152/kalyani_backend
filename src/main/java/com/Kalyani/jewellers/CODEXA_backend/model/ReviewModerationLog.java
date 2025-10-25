package com.Kalyani.jewellers.CODEXA_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name="review_moderation_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModerationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="review_id", nullable=false)
    private Long reviewId;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ModerationAction action;

    private String note;
    private String adminName;

    @Column(name="created_at", nullable=false, updatable=false, insertable=false,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;
}
