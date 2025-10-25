package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.model.Gem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GemDTO {
    private Integer gemId;
    private String name;
    private BigDecimal price;
    private String imageFileName;
    private String imageContentType;
    private Long imageFileSize;
    private String imageUrl;

    public static GemDTO fromEntity(Gem gem) {
        if (gem == null) return null;
        return GemDTO.builder()
                .gemId(gem.getGemId())
                .name(gem.getGemName())
                .price(gem.getKaratRate())
                .imageFileName(gem.getImageFileName())
                .imageContentType(gem.getImageContentType())
                .imageFileSize(gem.getImageFileSize())
                .imageUrl(gem.getImageData() != null ? 
                    "data:" + gem.getImageContentType() + ";base64," + 
                    java.util.Base64.getEncoder().encodeToString(gem.getImageData()) : null)
                .build();
    }

    public Gem toEntity() {
        Gem gem = new Gem();
        gem.setGemId(this.gemId);
        gem.setGemName(this.name);
        gem.setKaratRate(this.price);
        gem.setImageFileName(this.imageFileName);
        gem.setImageContentType(this.imageContentType);
        gem.setImageFileSize(this.imageFileSize);
        return gem;
    }
}
