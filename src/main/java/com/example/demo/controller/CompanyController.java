package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;
import com.example.demo.service.implementation.CompanyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CompanyDTO company) {
        companyService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.removeCompanyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
