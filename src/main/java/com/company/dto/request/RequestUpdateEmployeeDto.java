package com.company.dto.request;
import com.company.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestUpdateEmployeeDto {
    Long id;
    String password;
    String email;
    String avatar;
    @Builder.Default
    private ERole role=ERole.EMPLOYEE;
}
