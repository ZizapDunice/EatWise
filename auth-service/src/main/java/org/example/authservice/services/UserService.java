package org.example.authservice.services;

import org.example.authservice.DTO.response.LoginUserResponse;
import org.example.authservice.DTO.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID id);

}
