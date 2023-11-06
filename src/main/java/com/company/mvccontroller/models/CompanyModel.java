package com.company.mvccontroller.models;

import com.company.repository.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyModel {
    String title;
    HashMap<String, String> menuMap;
    List<Company> companyList;
}
