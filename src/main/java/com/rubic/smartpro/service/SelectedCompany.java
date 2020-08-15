package com.rubic.smartpro.service;

import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelectedCompany {

    private final CompanyRepository companyRepository;

    public SelectedCompany(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getSelectedCompany(){
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        return company.get();
    }
}
