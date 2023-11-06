package com.company.service;

import com.company.dto.request.RequestCompanyDto;
import com.company.repository.ICompanyRepository;
import com.company.repository.entity.Company;
import com.company.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends ServiceManager<Company, Long> {
    private final ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean existsCompanyByCompanyName(String companyName) {
        return repository.existsCompanyByCompanyName(companyName);
    }

    public Boolean register(RequestCompanyDto dto) {
        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .build();
        save(company);
        return true;
    }

    public Company findByCompanyName(String companyName) {
        return repository.findByCompanyName(companyName);
    }
}
