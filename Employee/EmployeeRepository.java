package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.Employee;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmployeeId(String employeeId);
}
