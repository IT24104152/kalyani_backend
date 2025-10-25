package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    Branch findByBranchCodeIgnoreCase(String branchCode);

    Branch findByBranchNameIgnoreCase(String branchName);
}