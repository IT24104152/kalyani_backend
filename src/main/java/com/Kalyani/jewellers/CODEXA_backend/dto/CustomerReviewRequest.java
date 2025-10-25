package com.Kalyani.jewellers.CODEXA_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerReviewRequest(
        @NotBlank @Size(max=80) String authorName,
        @Min(1) @Max(5) int rating,
        @NotBlank String comment
) {}