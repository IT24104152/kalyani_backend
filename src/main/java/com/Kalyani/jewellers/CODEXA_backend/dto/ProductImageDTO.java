package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.model.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private Integer imageId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private byte[] data;
    private String imageUrl; // Base64 encoded or URL for frontend

    public static ProductImageDTO fromEntity(ProductImage image) {
        if (image == null) return null;
        return ProductImageDTO.builder()
                .imageId(image.getImageId())
                .fileName(image.getFileName())
                .contentType(image.getContentType())
                .fileSize(image.getFileSize())
                .data(image.getData())
                .imageUrl("data:" + image.getContentType() + ";base64," + 
                    java.util.Base64.getEncoder().encodeToString(image.getData()))
                .build();
    }

    public ProductImage toEntity() {
        ProductImage image = new ProductImage();
        image.setImageId(this.imageId);
        image.setFileName(this.fileName);
        image.setContentType(this.contentType);
        image.setFileSize(this.fileSize);
        image.setData(this.data);
        return image;
    }
}

