package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketStatus;
import com.Kalyani.jewellers.CODEXA_backend.enums.ServiceTicketType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ServiceTicketUpdateDto {
    @NotBlank @Size(max = 100)
    private String customerFname;
    @NotBlank @Size(max = 100)
    private String customerLname;
    @NotBlank @Email @Size(max = 150)
    private String email;
    @NotBlank @Size(max = 20)
    private String contactNumber;
    private ServiceTicketType type;
    private LocalDate preferredDate;
    private String note;
    private ServiceTicketStatus status;

    public String getCustomerFname() { return customerFname; }
    public void setCustomerFname(String customerFname) { this.customerFname = customerFname; }
    public String getCustomerLname() { return customerLname; }
    public void setCustomerLname(String customerLname) { this.customerLname = customerLname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public ServiceTicketType getType() { return type; }
    public void setType(ServiceTicketType type) { this.type = type; }
    public LocalDate getPreferredDate() { return preferredDate; }
    public void setPreferredDate(LocalDate preferredDate) { this.preferredDate = preferredDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public ServiceTicketStatus getStatus() { return status; }
    public void setStatus(ServiceTicketStatus status) { this.status = status; }
}
