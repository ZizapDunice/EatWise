package org.example.calorycountingserver.services;

import org.example.calorycountingserver.DTO.request.CreateUserProfileRequest;
import org.example.calorycountingserver.DTO.response.CreateUserProfileResponse;
import org.example.calorycountingserver.models.AuthUser;
import org.example.calorycountingserver.models.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileService {
    CreateUserProfileResponse createUserProfile(CreateUserProfileRequest profile);
    Optional<UserProfile> getUserProfileByAuthUserId(UUID authUserId);
    CreateUserProfileResponse getProfile();
    UserProfile updateUserProfile(UserProfile profile);
    void deleteUserProfile(UUID authUserId);
    AuthUser getAuthUser(UUID authUserId);
}