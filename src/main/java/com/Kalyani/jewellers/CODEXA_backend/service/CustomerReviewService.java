package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.exception.dto.CustomerReviewRequest;
import com.Kalyani.jewellers.CODEXA_backend.model.CustomerReview;
import com.Kalyani.jewellers.CODEXA_backend.model.ModerationAction;
import com.Kalyani.jewellers.CODEXA_backend.model.ReviewModerationLog;
import com.Kalyani.jewellers.CODEXA_backend.model.ReviewStatus;
import com.Kalyani.jewellers.CODEXA_backend.repository.CustomerReviewRepository;
import com.Kalyani.jewellers.CODEXA_backend.repository.ReviewModerationLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.apache.commons.text.StringEscapeUtils;

import java.time.Instant;
import java.util.List;

@Service
public class CustomerReviewService {
    private final CustomerReviewRepository repo;
    private final ReviewModerationLogRepository logRepo;

    public CustomerReviewService(CustomerReviewRepository repo, ReviewModerationLogRepository logRepo) {
        this.repo = repo;
        this.logRepo = logRepo;
    }

    public List<CustomerReview> getPending() {
        return repo.findByStatusOrderByCreatedAtDesc(ReviewStatus.PENDING);
    }

    public CustomerReview submit(CustomerReviewRequest req) {
        CustomerReview cr = new CustomerReview();
        cr.setAuthorName(safe(req.authorName()));
        cr.setRating((short) Math.max(1, Math.min(5, req.rating())));
        cr.setComment(safe(req.comment()));
        cr.setStatus(ReviewStatus.PENDING);
        return repo.save(cr);
    }

    public CustomerReview approve(Long id, String adminName, String note) {
        CustomerReview cr = repo.findById(id).orElseThrow();
        cr.setStatus(ReviewStatus.APPROVED);
        cr.setModeratedAt(Instant.now());
        cr.setModeratedBy(adminName);
        cr.setModerationNote(note);
        CustomerReview saved = repo.save(cr);

        log(id, ModerationAction.APPROVED, note, adminName);
        return saved;
    }

    // CustomerReviewService.java (add this)
    public List<CustomerReview> getApprovedFourPlus() {
        return repo.findByStatusAndRatingGreaterThanEqualOrderByRatingDescCreatedAtDesc(
                ReviewStatus.APPROVED, (short)4);
    }


    public CustomerReview reject(Long id, String adminName, String note) {
        CustomerReview cr = repo.findById(id).orElseThrow();
        cr.setStatus(ReviewStatus.REJECTED);
        cr.setModeratedAt(Instant.now());
        cr.setModeratedBy(adminName);
        cr.setModerationNote(note);
        CustomerReview saved = repo.save(cr);

        log(id, ModerationAction.REJECTED, note, adminName);
        return saved;
    }


    @Transactional
    public void delete(Long id, String adminName) {
        CustomerReview cr = repo.findById(id).orElseThrow();
        if (cr.getDeletedAt() == null) {
            cr.setDeletedAt(Instant.now());
            cr.setStatus(ReviewStatus.DELETED); // optional: take out of any queue
            cr.setModeratedAt(Instant.now());
            cr.setModeratedBy(adminName);
            repo.save(cr);
            log(cr.getId(), ModerationAction.DELETED, null, adminName); // audit stays
        }
    }

    private void log(Long reviewId, ModerationAction action, String note, String admin) {
        ReviewModerationLog l = new ReviewModerationLog();
        l.setReviewId(reviewId);
        l.setAction(action);
        l.setNote(note);
        l.setAdminName(admin);
        logRepo.save(l);
    }

    private String safe(String s) {
        return s == null ? "" : StringEscapeUtils.escapeHtml4(s.trim());
    }

    public List<CustomerReview> getAdminQueue() {
        return repo.findAll();
    }
}
