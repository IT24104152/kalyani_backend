package com.Kalyani.jewellers.CODEXA_backend.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer roleId;
}
