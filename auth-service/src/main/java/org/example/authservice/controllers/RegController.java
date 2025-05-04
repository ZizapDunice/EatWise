package org.example.authservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.authservice.DTO.request.AuthUserRequest;
import org.example.authservice.DTO.request.RegisterUserRequest;
import org.example.authservice.DTO.response.LoginUserResponse;
import org.example.authservice.DTO.response.UserResponse;
import org.example.authservice.models.commonResponse.CustomSuccessResponse;
import org.example.authservice.services.AuthService;
import org.example.authservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegController {

    private final AuthService service;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CustomSuccessResponse<LoginUserResponse>> signUp(@Valid @RequestBody RegisterUserRequest userDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.signUp(userDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomSuccessResponse<LoginUserResponse>> signIn(@Valid @RequestBody AuthUserRequest userDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.signIn(userDto)));
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}