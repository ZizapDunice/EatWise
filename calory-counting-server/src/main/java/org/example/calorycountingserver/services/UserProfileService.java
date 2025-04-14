package org.example.calorycountingserver.services;

import org.example.calorycountingserver.models.AuthUser;
import org.example.calorycountingserver.models.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileService {
    UserProfile createUserProfile(UserProfile profile);
    Optional<UserProfile> getUserProfileByAuthUserId(UUID authUserId);
    UserProfile updateUserProfile(UserProfile profile);
    void deleteUserProfile(UUID authUserId);
    AuthUser getAuthUser(UUID authUserId);
}