package org.example.authservice.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.authservice.DTO.request.AuthUserRequest;
import org.example.authservice.DTO.request.RegisterUserRequest;
import org.example.authservice.DTO.response.LoginUserResponse;
import org.example.authservice.handling.CustomException;
import org.example.authservice.handling.ErrorCodes;
import org.example.authservice.mapper.UserMapper;
import org.example.authservice.models.UserEntity;
import org.example.authservice.repo.UserRepository;
import org.example.authservice.security.CustomUserDetails;
import org.example.authservice.security.JwtTokenGenerator;
import org.example.authservice.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    private final UserRepository repository;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginUserResponse signUp(RegisterUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCodes.USER_ALREADY_EXISTS);
        }

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            throw new CustomException(ErrorCodes.USER_ROLE_NOT_NULL);
        }

        UserEntity user = userMapper.fromRegisterUserRequestToUserEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity newUser = repository.save(user);

        return generateLoginResponse(newUser, "Пользователь успешно зарегистрирован");
    }

    @Transactional
    public LoginUserResponse signIn(AuthUserRequest request) {

        UserEntity user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCodes.PASSWORD_NOT_VALID);
        }

        return generateLoginResponse(user, "Успешный вход в систему");
    }

    private LoginUserResponse generateLoginResponse(UserEntity user, String message) {
        CustomUserDetails customUserDetails = new CustomUserDetails(user.getId(), user.getPassword(), user.getRoles());
        String jwt = jwtTokenGenerator.generateToken(customUserDetails);
        return userMapper.fromUserEntityToLoginUserResponse(user, jwt, message);
    }

}