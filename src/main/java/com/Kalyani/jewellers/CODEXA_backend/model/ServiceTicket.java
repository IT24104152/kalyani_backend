package com.Kalyani.jewellers.CODEXA_backend.model;

import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketStatus;
import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_ticket")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "branch_id")
    private Integer branchId;                 // optional FK → branch.branch_id

    @Column(name = "assigned_user_id")
    private Integer assignedUserId;           // optional FK → user.user_id

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ServiceTicketType type;        // CLEANING|REPAIR

    @Column(name = "customer_fname", nullable = false, length = 100)
    private String customerFname;

    @Column(name = "customer_lname", nullable = false, length = 100)
    private String customerLname;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "preferred_date")
    private LocalDate preferredDate;       // DATE

    @Column(name = "ticket_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ticketDate = LocalDateTime.now();      // DB default applies if null

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ServiceTicketStatus status;    // NEW|IN_PROGRESS|DONE|CANCELLED
}
