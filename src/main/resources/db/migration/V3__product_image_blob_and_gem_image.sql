/* V3: Product images as BLOBs (no URL), remove any image↔gem link,
       and add a single image directly on gem.
   Notes:
   - No new foreign keys are added here.
   - We do NOT use generated columns or unique partial indexes in this pass.
*/

/* ---------- product_image: detach from gem, add BLOB fields ---------- */



ALTER TABLE product_image
DROP FOREIGN KEY fk_image_gem;

-- Drop the gem_id index if it exists.
DROP INDEX idx_image_gem ON product_image;

-- Remove the gem_id column (images belong to products only).
ALTER TABLE product_image
DROP COLUMN gem_id;

-- Add metadata + BLOB + primary flag (NULL first to be migration-safe).
ALTER TABLE product_image
    ADD COLUMN file_name     VARCHAR(255)     NULL AFTER product_id,
  ADD COLUMN content_type  VARCHAR(100)     NULL AFTER file_name,
  ADD COLUMN file_size     BIGINT UNSIGNED  NULL AFTER content_type,
  ADD COLUMN data          LONGBLOB         NULL AFTER file_size,
  ADD COLUMN is_primary    BOOLEAN NOT NULL DEFAULT FALSE AFTER data,
  ADD COLUMN created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER alt_text;

-- Fast lookup for (product_id, is_primary)
CREATE INDEX idx_product_primary ON product_image (product_id, is_primary);

-- We no longer store URLs in product_image
ALTER TABLE product_image
DROP COLUMN url;

-- Tighten nullability (skip these 4 MODIFYs if you need to backfill first).
ALTER TABLE product_image
    MODIFY file_name    VARCHAR(255)    NOT NULL,
    MODIFY content_type VARCHAR(100)    NOT NULL,
    MODIFY file_size    BIGINT UNSIGNED NOT NULL,
    MODIFY data         LONGBLOB        NOT NULL;

/* ---------- gem: add a single (optional) image directly on the row ---------- */

ALTER TABLE gem
    ADD COLUMN image_file_name     VARCHAR(255)     NULL AFTER gem_name,
  ADD COLUMN image_content_type  VARCHAR(100)     NULL AFTER image_file_name,
  ADD COLUMN image_file_size     BIGINT UNSIGNED  NULL AFTER image_content_type,
  ADD COLUMN image_data          LONGBLOB         NULL AFTER image_file_size,
  ADD COLUMN image_created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER image_data;
