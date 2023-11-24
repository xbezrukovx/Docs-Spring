package com.example.demo.service;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.model.Company;

import java.util.List;

public interface CompanyService {
    void createCompany(CompanyDTO companyDTO);
    List<Company> getAllCompanies();
    void removeCompanyById(Long id);
}
