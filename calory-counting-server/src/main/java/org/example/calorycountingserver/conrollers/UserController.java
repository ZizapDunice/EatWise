package org.example.calorycountingserver.conrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calorycountingserver.DTO.request.CreateUserProfileRequest;
import org.example.calorycountingserver.DTO.response.CreateUserProfileResponse;
import org.example.calorycountingserver.models.commonResponse.BaseSuccessResponse;
import org.example.calorycountingserver.models.commonResponse.CustomSuccessResponse;
import org.example.calorycountingserver.services.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService service;

    @PostMapping("/create")
    public ResponseEntity<CustomSuccessResponse<CreateUserProfileResponse>> createUserProfile(@Valid @RequestBody CreateUserProfileRequest userDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.createUserProfile(userDto)));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<CustomSuccessResponse<CreateUserProfileResponse>> getProfile() {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.getProfile()));
    }

}
