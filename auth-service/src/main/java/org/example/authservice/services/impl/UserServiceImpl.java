package org.example.authservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.DTO.response.LoginUserResponse;
import org.example.authservice.DTO.response.UserResponse;
import org.example.authservice.handling.CustomException;
import org.example.authservice.handling.ErrorCodes;
import org.example.authservice.mapper.UserMapper;
import org.example.authservice.repo.UserRepository;
import org.example.authservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse getUserById(UUID id) {
        return mapper.fromUserEntityToUserResponse(repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND)));
    }
}
