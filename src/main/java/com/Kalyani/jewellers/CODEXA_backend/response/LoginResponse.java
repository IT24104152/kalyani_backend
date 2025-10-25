package com.Kalyani.jewellers.CODEXA_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Integer userId;
    private Integer roleId;
    private String roleName;
    private String userFname;
    private String userLname;
}
