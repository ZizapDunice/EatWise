package org.example.calorycountingserver.services.impl;

import jakarta.transaction.Transactional;
import org.example.calorycountingserver.models.UserProfile;
import org.example.calorycountingserver.repo.UserProfileRepo;
import org.example.calorycountingserver.services.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepo userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile createUserProfile(UserProfile profile) {
        // При создании можно проверить, существует ли уже профиль с таким authUserId
        if(userProfileRepository.findByAuthUserId(profile.getAuthUserId()).isPresent()){
            throw new IllegalArgumentException("Профиль для данного пользователя уже существует");
        }
        return userProfileRepository.save(profile);
    }

    @Override
    public Optional<UserProfile> getUserProfileByAuthUserId(UUID authUserId) {
        return userProfileRepository.findByAuthUserId(authUserId);
    }

    @Override
    public UserProfile updateUserProfile(UserProfile profile) {
        // Можно добавить проверку на существование записи
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
}
