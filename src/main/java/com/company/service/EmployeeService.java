package com.company.service;

import com.company.dto.request.RegisterEmployeeRequestDto;
import com.company.dto.request.RequestUpdateEmployeeDto;
import com.company.exception.BasicCompanyAppProjectException;
import com.company.exception.EErrorType;
import com.company.repository.IEmployeeRepository;
import com.company.repository.entity.Company;
import com.company.repository.entity.Employee;
import com.company.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Boolean updateById(RequestUpdateEmployeeDto dto) {
        Optional<Employee> employee = repository.findById(dto.getId());
        if (employee.isEmpty()){
            throw new BasicCompanyAppProjectException(EErrorType.USER_NOT_BE_FOUND);
        }
        employee.get().setName(dto.getName());
        employee.get().setSurname(dto.getSurname());
        employee.get().setEmail(dto.getEmail());
        update(employee.get());
        return true;
    }
}
