package com.pdf.pdfgeneration.controller;


import com.pdf.pdfgeneration.model.Employee;
import com.pdf.pdfgeneration.repository.EmployeeRepository;
import com.pdf.pdfgeneration.service.DatabasePDFService;
import com.pdf.pdfgeneration.service.PdfService;
import org.hibernate.dialect.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired private DatabasePDFService databasePDFService;

    @PostMapping("/createpdf")
    public ResponseEntity<InputStreamResource> createpdf(){
        ByteArrayInputStream pdf=pdfService.createPdf();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline;file=certificate.pdf");

        return ResponseEntity.ok()
                .header(String.valueOf(httpHeaders))
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));

    }


    //i had to put the data in the db manually for this project.

    @GetMapping("/fetchtopdf")
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException{
        List<Employee> employees=(List<Employee>) employeeRepository.findAll();
        ByteArrayInputStream bis=DatabasePDFService.employeePDFReport(employees);

        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline; filename=Certificate.pdf");

        return ResponseEntity.ok().header(String.valueOf(headers)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

}
