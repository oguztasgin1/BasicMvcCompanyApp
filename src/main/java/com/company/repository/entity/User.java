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
@Table(name = "tbluser")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String username;
    String password;
    String email;
    String avatar;
    @Builder.Default
    private ERole role=ERole.COMPANY_MANAGER;
}
