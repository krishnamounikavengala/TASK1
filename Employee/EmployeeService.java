package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Employee;
import com.example.Repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public double calculateTax(String employeeId) {
        // Fetching the employee, or throwing an error if not found
        Employee employee = repository.findById(employeeId).orElseThrow(() -> 
            new IllegalArgumentException("Employee not found with ID: " + employeeId));

        LocalDate now = LocalDate.now();
        
        // Calculate months worked between date of joining and today
        long monthsWorked = ChronoUnit.MONTHS.between(employee.getDoj(), now);
        
        // Ensure that the employee has worked for at least 1 month to calculate tax
        if (monthsWorked < 1) {
            throw new IllegalArgumentException("Employee has not worked for a full month yet");
        }

        // Annual salary divided by 12 to get monthly salary, then multiplied by months worked
        double proratedSalary = (employee.getSalary() / 12) * monthsWorked;

        // Debugging logs (optional to remove after debugging)
        System.out.println("Employee Salary: " + employee.getSalary());
        System.out.println("Months Worked: " + monthsWorked);
        System.out.println("Prorated Salary: " + proratedSalary);

        // Tax calculation logic
        double tax = 0.0;
        if (proratedSalary > 250000) {
            if (proratedSalary <= 500000) {
                tax = (proratedSalary - 250000) * 0.05;
            } else if (proratedSalary <= 1000000) {
                tax = (500000 - 250000) * 0.05 + (proratedSalary - 500000) * 0.10;
            } else {
                tax = (500000 - 250000) * 0.05 + (1000000 - 500000) * 0.10 + (proratedSalary - 1000000) * 0.20;
            }
        } else {
            // If the prorated salary is below the taxable limit, log the information (optional)
            System.out.println("Tax is 0 because prorated salary is below 250000");
        }

        // Return the calculated tax
        return tax;
    }

}
