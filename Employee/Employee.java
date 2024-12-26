package com.example.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @Pattern(regexp = "E\\d{3}", message = "Employee ID must match the format E123")
    private String employeeId;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @ElementCollection
    @Size(min = 1, message = "At least one phone number is required")
    private List<@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") String> phoneNumbers;

    @NotNull(message = "Date of joining is mandatory")
    private LocalDate doj;

    @Positive(message = "Salary must be positive")
    private Double salary;
}
