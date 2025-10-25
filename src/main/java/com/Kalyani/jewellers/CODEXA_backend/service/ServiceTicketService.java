package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketStatus;
import com.Kalyani.jewellers.CODEXA_backend.model.ServiceTicket;
import com.Kalyani.jewellers.CODEXA_backend.repository.ServiceTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceTicketService {

    private final ServiceTicketRepository repo;

    //get service tickets
    public List<ServiceTicket> findAll() { return repo.findAll(); }

    //find tickets by id
    public Optional<ServiceTicket> findById(int id) { return repo.findById(id); }

    //create service tickets
    public ServiceTicket create(ServiceTicket ticket) {
        if (ticket.getStatus() == null) ticket.setStatus(ServiceTicketStatus.NEW);
        return repo.save(ticket);
    }

    //update service tickets
    public ServiceTicket update(int id, ServiceTicket incoming) {
        return repo.findById(id).map(existing -> {
            incoming.setServiceId(existing.getServiceId());
            return repo.save(incoming);
        }).orElseThrow(() -> new IllegalArgumentException("ServiceTicket not found: " + id));
    }

    //delete service tickets
    public void delete(int id) { repo.deleteById(id); }
}
