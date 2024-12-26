package com.example.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Entities.Employee;
import com.example.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmployee = service.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{employeeId}/tax-deductions")
    public ResponseEntity<?> getTaxDeductions(@PathVariable String employeeId) {
        try {
            double tax = service.calculateTax(employeeId);
            return ResponseEntity.ok().body(tax);
        } catch (IllegalArgumentException e) {
            // Handling error if employee is not found
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
