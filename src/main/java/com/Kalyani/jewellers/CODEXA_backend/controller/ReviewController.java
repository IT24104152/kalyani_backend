package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.exception.dto.CustomerReviewRequest;
import com.Kalyani.jewellers.CODEXA_backend.exception.dto.PublicReviewDto;
import com.Kalyani.jewellers.CODEXA_backend.model.CustomerReview;
import com.Kalyani.jewellers.CODEXA_backend.service.CustomerReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:8080")
public class ReviewController {
    private final CustomerReviewService customer;

    public ReviewController(CustomerReviewService customer) {
        this.customer = customer;
    }

    // Admin queue: get all reviews
    @GetMapping("/adminQueue")
    public List<CustomerReview> adminQueue() {
        return customer.getAdminQueue();
    }

    // Public endpoint: Approved customer
    @GetMapping("/public")
    public List<PublicReviewDto> getPublic() {
        return customer.getApprovedFourPlus().stream()
                .map(cr -> new PublicReviewDto(
                        String.valueOf(cr.getId()),
                        cr.getAuthorName(),
                        cr.getRating(),
                        cr.getComment(),
                        cr.getCreatedAt() == null ? Instant.now() : cr.getCreatedAt()
                ))
                .toList();
    }

    // Public: customer submits a review → PENDING
    @PostMapping("/addReview")
    public ResponseEntity<?> submit(@Valid @RequestBody CustomerReviewRequest req) {
        var saved = customer.submit(req);
        return ResponseEntity.ok(saved.getId());
    }

    // Admin queue
    @GetMapping("/pending")
    public List<CustomerReview> pending() {
        return customer.getPending();
    }

    // Approve
    /*@PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerReview approve(@PathVariable Long id,
                                  @RequestHeader(value="X-Admin-Name", required=false) String admin,
                                  @RequestParam(value="note", required=false) String note) {
        return customer.approve(id, admin == null ? "admin" : admin, note);
    }*/
    @PutMapping("/{id}/approve")
    public CustomerReview approve(@PathVariable Long id,
                                  @RequestParam(value="note", required=false) String note,
                                  Authentication authentication) {
        String adminName = authentication.getName();
        return customer.approve(id, adminName, note);
    }


    // Reject
    @PutMapping("/{id}/reject")
    public CustomerReview reject(@PathVariable Long id,
                                 @RequestParam(value="note", required=false) String note,
                                 Authentication authentication) {
        String adminName = authentication.getName();
        return customer.reject(id, adminName, note);
    }

    // Delete
    @PutMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    Authentication authentication) {
        String adminName = authentication.getName();
        customer.delete(id, adminName);
        return ResponseEntity.ok().body(
                java.util.Map.of("message", "Review " + id + " deleted successfully"));
    }
}