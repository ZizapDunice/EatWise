package org.example.calorycountingserver.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calorycountingserver.DTO.request.CreateUserProfileRequest;
import org.example.calorycountingserver.DTO.response.CreateUserProfileResponse;
import org.example.calorycountingserver.handling.CustomException;
import org.example.calorycountingserver.handling.ErrorCodes;
import org.example.calorycountingserver.mappers.UserProfileMapper;
import org.example.calorycountingserver.models.AuthUser;
import org.example.calorycountingserver.models.UserProfile;
import org.example.calorycountingserver.repo.UserProfileRepo;
import org.example.calorycountingserver.services.UserProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final RestTemplate restTemplate;

    @Value("${auth-service.url}")
    private String userServiceUrl;

    @Override
    public CreateUserProfileResponse createUserProfile(CreateUserProfileRequest profile) {

        AuthUser authUser = getUserById(UUID.fromString(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));

        UserProfile user = userProfileMapper.toEntity(profile, authUser.getId());

        if(userProfileRepository.findByAuthUserId(user.getAuthUserId()).isPresent()){
            throw new IllegalArgumentException("Профиль для данного пользователя уже существует");
        }

        userProfileRepository.save(user);

        return userProfileMapper.toDto(user, authUser);
    }

    @Override
    public Optional<UserProfile> getUserProfileByAuthUserId(UUID authUserId) {
        return userProfileRepository.findByAuthUserId(authUserId);
    }

    @Override
    public CreateUserProfileResponse getProfile() {

        AuthUser authUser = getUserById(UUID.fromString(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));

        UserProfile user = userProfileRepository.findByAuthUserId(authUser.getId()).orElseThrow(
                () -> new CustomException(ErrorCodes.USER_NOT_FOUND)
        );

        return userProfileMapper.toDto(user, authUser);
    }

    @Override
    public UserProfile updateUserProfile(UserProfile profile) {
        Optional<UserProfile> existing = userProfileRepository.findByAuthUserId(profile.getAuthUserId());
        if(existing.isEmpty()){
            throw new IllegalArgumentException("Профиль не найден");
        }
        profile.setId(existing.get().getId());
        return userProfileRepository.save(profile);
    }

    @Override
    public void deleteUserProfile(UUID authUserId) {
        Optional<UserProfile> existing = userProfileRepository.findByAuthUserId(authUserId);
        existing.ifPresent(userProfileRepository::delete);
    }

    @Override
    public AuthUser getAuthUser(UUID authUserId) {
        return getUserById(authUserId);
    }

    public AuthUser getUserById(UUID userId) {
        String url = userServiceUrl + "/auth/users/" + userId;
        return restTemplate.getForObject(url, AuthUser.class);
    }
}
