package com.company.repository;

import com.company.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsCompanyByCompanyName(String companyName);

    Company findByCompanyName(String companyName);
}
