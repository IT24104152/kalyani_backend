package com.Kalyani.jewellers.CODEXA_backend.dto;

import java.time.Instant;

public record PublicReviewDto(
        String id,                 // null for Google items
        String authorName,
        int rating,
        String comment,
        Instant createdAt
) {}