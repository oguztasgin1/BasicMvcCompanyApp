package com.company.dto.request;
import com.company.repository.entity.Company;
import com.company.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterEmployeeRequestDto {
    String name;
    String surname;
    String email;
    String companyName;
}
