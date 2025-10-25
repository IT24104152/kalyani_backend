package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.ServiceTicket;
import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Integer> {
    List<ServiceTicket> findByStatus(ServiceTicketStatus status);
    List<ServiceTicket> findByBranchId(Integer branchId);
}
