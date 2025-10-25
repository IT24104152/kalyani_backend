package com.Kalyani.jewellers.CODEXA_backend.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String passwordHash;
}
