package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.BranchDto;
import com.Kalyani.jewellers.CODEXA_backend.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080") // or 5173 for Vite
@RestController
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

    // Constructor Injection
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/allBranches")
    public ResponseEntity<List<BranchDto>> getAllBranches(){
        return new ResponseEntity<>(branchService.getAllBranches(), HttpStatus.OK) ;
    }

    @GetMapping("/searchByName/{branchName}")
    public ResponseEntity<BranchDto> getBranchesByName(@PathVariable String branchName){
        BranchDto branchesByName = branchService.getBranchByName(branchName);
        return new ResponseEntity<>(branchesByName, HttpStatus.OK);
    }

    @PostMapping("/addBranch")
    public ResponseEntity<BranchDto> addBranch(@RequestBody BranchDto branchDto){
        BranchDto savedBranch = branchService.addBranch(branchDto);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @PutMapping("/updateBranch/{branchCode}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable("branchCode") String branchCode, @RequestBody BranchDto branch){
        return new ResponseEntity<>(branchService.updateBranch(branchCode, branch), HttpStatus.OK) ;
    }

    @DeleteMapping("/deleteBranch/{branchCode}")
    public ResponseEntity<String> removeBranch(@PathVariable("branchCode") String branchCode){
        return new ResponseEntity<>(branchService.deleteBranch(branchCode), HttpStatus.OK);
    }

}