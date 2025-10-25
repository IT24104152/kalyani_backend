package com.Kalyani.jewellers.CODEXA_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CustomDesignCreateDto {
    @NotBlank @Size(max = 100)
    private String customerFname;
    @NotBlank @Size(max = 100)
    private String customerLname;
    @NotBlank @Email @Size(max = 150)
    private String email;
    @NotBlank @Size(max = 20)
    private String contactNumber;

    private BigDecimal budget; // optional

    // For now backend expects a single image URL (per existing entity)
    @NotBlank @Size(max = 255)
    private String image;

    private Integer preferredMetalId; // optional

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
    public Integer getPreferredMetalId() { return preferredMetalId; }
    public void setPreferredMetalId(Integer preferredMetalId) { this.preferredMetalId = preferredMetalId; }
}
