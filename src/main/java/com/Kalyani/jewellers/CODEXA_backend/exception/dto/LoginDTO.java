package com.Kalyani.jewellers.CODEXA_backend.exception.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String passwordHash;
}
