package com.Kalyani.jewellers.CODEXA_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Used to transfer data between client and the server
public class BranchDto {
    private Integer branchId;
    private String branchName;
    private String branchCode;
    private String branchAddress;
    private String branchTelephone;
    private String branchHours;
}
