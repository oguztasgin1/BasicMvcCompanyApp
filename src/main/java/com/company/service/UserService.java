package com.company.service;

import com.company.dto.request.LoginRequestDto;
import com.company.dto.request.RegisterRequestDto;
import com.company.repository.IUserRepository;
import com.company.repository.entity.User;
import com.company.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final IUserRepository repository;
    public UserService(IUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<User> findOptionalByUsernameAndPassword(LoginRequestDto dto) {
        return  repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

    public Boolean existsUserByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    public Boolean register(RegisterRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .build();
        save(user);
        return true;
    }
}
