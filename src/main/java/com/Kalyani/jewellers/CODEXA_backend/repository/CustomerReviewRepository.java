package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.CustomerReview;
import com.Kalyani.jewellers.CODEXA_backend.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {
    List<CustomerReview> findByStatusOrderByRatingDescCreatedAtDesc(ReviewStatus status);
    List<CustomerReview> findByStatusOrderByCreatedAtDesc(ReviewStatus status);
    // CustomerReviewRepository.java
    List<CustomerReview> findByStatusAndRatingGreaterThanEqualOrderByRatingDescCreatedAtDesc(
            ReviewStatus status, short minRating);

    List<CustomerReview> findByStatusAndDeletedAtIsNullOrderByCreatedAtDesc(ReviewStatus status);

}