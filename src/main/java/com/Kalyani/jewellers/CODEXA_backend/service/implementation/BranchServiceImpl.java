package com.Kalyani.jewellers.CODEXA_backend.service.implementation;

import com.Kalyani.jewellers.CODEXA_backend.dto.BranchDto;
import com.Kalyani.jewellers.CODEXA_backend.mapper.BranchMapper;
import com.Kalyani.jewellers.CODEXA_backend.model.Branch;
import com.Kalyani.jewellers.CODEXA_backend.repository.BranchRepository;
import com.Kalyani.jewellers.CODEXA_backend.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepo;

    @Override
    public BranchDto addBranch(BranchDto branchDto) {
        Branch branch = BranchMapper.mapToBranch(branchDto);
        Branch savedBranch = branchRepo.save(branch);

        return BranchMapper.mapToBranchDto(savedBranch);
    }

    @Override
    public BranchDto getBranchByName(String branchName) {
        Branch branchesByName = branchRepo.findByBranchNameIgnoreCase(branchName);
        return BranchMapper.mapToBranchDto(branchesByName);
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branches = branchRepo.findAll();
        return branches.stream().map((branch) -> BranchMapper.mapToBranchDto(branch))
                .collect(Collectors.toList());
    }

    @Override
    public BranchDto updateBranch(String branchCode, BranchDto updatedBranchDto) {
        if(branchRepo.findByBranchCodeIgnoreCase(branchCode) == null) {
            System.out.println("A branch with code " + branchCode + " does not exist. Create a new branch.");
            return null;
        } else {
            Branch existingBranch = branchRepo.findByBranchCodeIgnoreCase(branchCode);

            existingBranch.setBranchName(updatedBranchDto.getBranchName());
            existingBranch.setBranchCode(updatedBranchDto.getBranchCode());
            existingBranch.setBranchAddress(updatedBranchDto.getBranchAddress());
            existingBranch.setBranchTelephone(updatedBranchDto.getBranchTelephone());
            existingBranch.setBranchHours(updatedBranchDto.getBranchHours());

            return BranchMapper.mapToBranchDto(branchRepo.save(existingBranch));
        }
    }

    @Override
    public String deleteBranch(String branchCode) {
        if (branchRepo.findByBranchCodeIgnoreCase(branchCode) == null) {
            return "Branch with id: " + branchCode + " does not exist.";
        } else {
            Branch branchToDlt = branchRepo.findByBranchCodeIgnoreCase(branchCode);
            Integer branchId = branchToDlt.getBranchId();
            branchRepo.deleteById(branchId);
            return "Branch with id: " + branchCode + " has been deleted";
        }
    }

}
