package com.company.repository;

import com.company.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    Boolean existsEmployeeByEmail(String email);
    List<Employee> findByCompany_CompanyName(String companyName);
}
