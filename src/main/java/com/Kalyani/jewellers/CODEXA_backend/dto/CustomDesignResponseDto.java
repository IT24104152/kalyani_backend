package com.Kalyani.jewellers.CODEXA_backend.dto;

import com.Kalyani.jewellers.CODEXA_backend.enums.CustomDesignStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomDesignResponseDto {
    private Integer designId;
    private String customerFname;
    private String customerLname;
    private String email;
    private String contactNumber;
    private BigDecimal budget;
    private String image;
    private LocalDateTime ticketDate;
    private CustomDesignStatus status;
    private Integer preferredMetalId;

    public Integer getDesignId() { return designId; }
    public void setDesignId(Integer designId) { this.designId = designId; }
    public String getCustomerFname() { return customerFname; }
    public void setCustomerFname(String customerFname) { this.customerFname = customerFname; }
    public String getCustomerLname() { return customerLname; }
    public void setCustomerLname(String customerLname) { this.customerLname = customerLname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public LocalDateTime getTicketDate() { return ticketDate; }
    public void setTicketDate(LocalDateTime ticketDate) { this.ticketDate = ticketDate; }
    public CustomDesignStatus getStatus() { return status; }
    public void setStatus(CustomDesignStatus status) { this.status = status; }
    public Integer getPreferredMetalId() { return preferredMetalId; }
    public void setPreferredMetalId(Integer preferredMetalId) { this.preferredMetalId = preferredMetalId; }
}
