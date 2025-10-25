package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketStatus;
import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceTicketResponseDto {
    private Integer serviceId;
    private Integer branchId;
    private Integer assignedUserId;
    private ServiceTicketType type;
    private String customerFname;
    private String customerLname;
    private String contactNumber;
    private String email;
    private LocalDate preferredDate;
    private LocalDateTime ticketDate;
    private String note;
    private ServiceTicketStatus status;

    public Integer getServiceId() { return serviceId; }
    public void setServiceId(Integer serviceId) { this.serviceId = serviceId; }
    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
    public Integer getAssignedUserId() { return assignedUserId; }
    public void setAssignedUserId(Integer assignedUserId) { this.assignedUserId = assignedUserId; }
    public ServiceTicketType getType() { return type; }
    public void setType(ServiceTicketType type) { this.type = type; }
    public String getCustomerFname() { return customerFname; }
    public void setCustomerFname(String customerFname) { this.customerFname = customerFname; }
    public String getCustomerLname() { return customerLname; }
    public void setCustomerLname(String customerLname) { this.customerLname = customerLname; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getPreferredDate() { return preferredDate; }
    public void setPreferredDate(LocalDate preferredDate) { this.preferredDate = preferredDate; }
    public LocalDateTime getTicketDate() { return ticketDate; }
    public void setTicketDate(LocalDateTime ticketDate) { this.ticketDate = ticketDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public ServiceTicketStatus getStatus() { return status; }
    public void setStatus(ServiceTicketStatus status) { this.status = status; }
}
