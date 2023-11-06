package com.company.service;

import com.company.dto.request.RegisterEmployeeRequestDto;
import com.company.repository.IEmployeeRepository;
import com.company.repository.entity.Company;
import com.company.repository.entity.Employee;
import com.company.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends ServiceManager<Employee, Long> {
    private final IEmployeeRepository repository;
    private final CompanyService companyService;

    public EmployeeService(IEmployeeRepository repository, CompanyService companyService) {
        super(repository);
        this.repository = repository;
        this.companyService = companyService;
    }

    public Boolean existsEmployeeByEmail(String email) {
        return repository.existsEmployeeByEmail(email);
    }

    public Boolean register(RegisterEmployeeRequestDto dto) {
        Company company = companyService.findByCompanyName(dto.getCompanyName());
        Employee employee = Employee.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .company(company)
                .build();
        save(employee);
        return true;
    }

    public List<Employee> findByCompany_CompanyName(String companyName) {
        return repository.findByCompany_CompanyName(companyName);
    }
}
