package com.pdf.pdfgeneration.repository;

import com.pdf.pdfgeneration.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //i had to put the data in the db manually for this project.

}
