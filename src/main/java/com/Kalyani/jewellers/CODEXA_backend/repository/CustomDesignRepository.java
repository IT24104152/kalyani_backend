package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.CustomDesign;
import com.Kalyani.jewellers.CODEXA_backend.enums.CustomDesignStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomDesignRepository extends JpaRepository<CustomDesign, Integer> {
    List<CustomDesign> findByStatus(CustomDesignStatus status);
    List<CustomDesign> findByAssignedUserId(Integer userId);
}
