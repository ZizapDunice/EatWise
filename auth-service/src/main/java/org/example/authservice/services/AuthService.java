package org.example.authservice.services;

import org.example.authservice.DTO.request.AuthUserRequest;
import org.example.authservice.DTO.request.RegisterUserRequest;
import org.example.authservice.DTO.response.LoginUserResponse;

public interface AuthService {

    LoginUserResponse signUp(RegisterUserRequest request);

    LoginUserResponse signIn(AuthUserRequest request);
}