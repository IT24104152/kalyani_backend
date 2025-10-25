-- Create reviews tables
CREATE TABLE customer_reviews (
                                  id                BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                                  author_name     VARCHAR(80) NOT NULL,
                                  rating          TINYINT UNSIGNED NOT NULL,
                                  comment         TEXT        NOT NULL,
                                  status          ENUM('PENDING','APPROVED','REJECTED', 'DELETED') NOT NULL DEFAULT 'PENDING',
                                  created_at      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  moderated_at    TIMESTAMP NULL,
                                  moderated_by    VARCHAR(80) NULL,
                                  moderation_note VARCHAR(255) NULL,
                                  deleted_at      TIMESTAMP NULL,
                                  CONSTRAINT chk_rating_range CHECK (rating BETWEEN 1 AND 5)
) ENGINE=InnoDB;

CREATE INDEX idx_reviews_status_created ON customer_reviews (status, created_at DESC);
CREATE INDEX idx_reviews_status_rating  ON customer_reviews (status, rating DESC, created_at DESC);

CREATE TABLE review_moderation_log (
                                       id         BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                                       review_id  BIGINT UNSIGNED NOT NULL,
                                       action     ENUM('APPROVED','REJECTED','DELETED') NOT NULL,
                                       note       VARCHAR(255) NULL,
                                       admin_name VARCHAR(80) NULL,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       CONSTRAINT fk_modlog_review FOREIGN KEY (review_id) REFERENCES customer_reviews (id) ON DELETE SET DEFAULT
) ENGINE=InnoDB;