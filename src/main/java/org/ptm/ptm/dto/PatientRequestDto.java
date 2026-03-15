package org.ptm.ptm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PatientRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max=100,message="Name Cannot be over 100")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }





    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message="Address cannot be blank")
    private String address;
    @NotBlank(message = "Date of Birth required")
    private String dateOfBirth;


}
