package com.company.repository.entity;
import com.company.repository.enums.ERole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblemployee")
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String avatar;
    @Builder.Default
    private ERole role=ERole.EMPLOYEE;
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}
