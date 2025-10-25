package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.model.Metal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetalDTO {
    private Integer metalId;
    private String metalType;
    private String metalPurity;

    public static MetalDTO fromEntity(Metal metal) {
        if (metal == null) return null;
        return MetalDTO.builder()
                .metalId(metal.getMetalId())
                .metalType(metal.getMetalType().toString())
                .metalPurity(metal.getMetalPurity())
                .build();
    }

    public Metal toEntity() {
        Metal metal = new Metal();
        metal.setMetalId(this.metalId);
        metal.setMetalType(Metal.MetalType.valueOf(this.metalType));
        metal.setMetalPurity(this.metalPurity);
        return metal;
    }
}
