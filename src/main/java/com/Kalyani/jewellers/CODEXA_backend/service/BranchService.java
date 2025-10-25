package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.BranchDto;

import java.util.List;

public interface BranchService {
    BranchDto addBranch(BranchDto branchDto);

    BranchDto getBranchByName(String branchName);

    List<BranchDto> getAllBranches();

    BranchDto updateBranch(String branchCode, BranchDto branchDto);

    String deleteBranch(String branchCode);
}
