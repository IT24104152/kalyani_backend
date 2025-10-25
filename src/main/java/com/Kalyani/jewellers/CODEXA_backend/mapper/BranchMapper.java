package com.Kalyani.jewellers.CODEXA_backend.mapper;

import com.Kalyani.jewellers.CODEXA_backend.dto.BranchDto;
import com.Kalyani.jewellers.CODEXA_backend.model.Branch;

public class BranchMapper {
    public static BranchDto mapToBranchDto(Branch branch) {
        return new BranchDto(
                branch.getBranchId(),
                branch.getBranchName(),
                branch.getBranchCode(),
                branch.getBranchAddress(),
                branch.getBranchTelephone(),
                branch.getBranchHours()
        );
    }

    public static Branch mapToBranch(BranchDto branchDto) {
        return new  Branch(
                branchDto.getBranchId(),
                branchDto.getBranchName(),
                branchDto.getBranchCode(),
                branchDto.getBranchAddress(),
                branchDto.getBranchTelephone(),
                branchDto.getBranchHours()
        );
    }
}
