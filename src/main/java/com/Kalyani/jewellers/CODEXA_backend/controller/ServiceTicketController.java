package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.model.ServiceTicket;
import com.Kalyani.jewellers.CODEXA_backend.service.ServiceTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/serviceticket")
@RequiredArgsConstructor
public class ServiceTicketController {

    private final ServiceTicketService service;

    /**
     * Retrieves all service tickets from the system.
     * @return List of all ServiceTicket objects
     */
    @GetMapping("/tickets")
    public List<ServiceTicket> list() {
        return service.findAll();
    }

    /**
     * Retrieves a specific service ticket by its ID.
     * @param id ID of the service ticket to fetch
     * @return ServiceTicket object if found, otherwise null
     */
    @GetMapping("/{id}")
    public ServiceTicket get(@PathVariable int id) {
        return service.findById(id).orElse(null);
    }

    /**
     * Creates a new service ticket.
     * @param ticket ServiceTicket object containing ticket details
     * @return ResponseEntity containing the saved ticket and WhatsApp URL
     */
    @PostMapping("/create")
    public ServiceTicket create(@RequestBody ServiceTicket ticket) {
        return service.create(ticket);
    }

    /**
     * Updates an existing service ticket by its ID.
     * @param id ID of the ticket to update
     * @param ticket ServiceTicket object containing updated details
     * @return Updated ServiceTicket object
     */
    @PutMapping("/{id}")
    public ServiceTicket update(@PathVariable Integer id, @RequestBody ServiceTicket ticket) {
        return service.update(id, ticket);
    }

    /**
     * Deletes a service ticket by its ID.
     * @param id ID of the ticket to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
