package com.example.demo.service.implementation;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {
    @Autowired
    CompanyRepository companyRepo;

    public void createCompany(CompanyDTO companyDTO) {
        Company company = Company.builder()
                .head(companyDTO.getHead())
                .actualAddress(companyDTO.getActualAddress())
                .legalAddress(companyDTO.getLegalAddress())
                .name(companyDTO.getName())
                .build();
        companyRepo.save(company);
    }

    public List<Company> getAllCompanies() {
        Iterable<Company> sourceCompanies = companyRepo.findAll();
        List<Company> targetCompanies = new ArrayList<>();
        sourceCompanies.forEach(targetCompanies::add);
        return targetCompanies;
    }

    public void removeCompanyById(Long id) {
        companyRepo.deleteById(id);
    }
}
