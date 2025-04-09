package org.example.calorycountingserver.services.impl;

import org.example.calorycountingserver.models.MealEntry;
import org.example.calorycountingserver.models.UserProfile;
import org.example.calorycountingserver.repo.MealEntryRepo;
import org.example.calorycountingserver.services.MealService;
import org.example.calorycountingserver.services.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MealServiceImpl implements MealService {

    private final MealEntryRepo mealEntryRepository;
    private final UserProfileService userProfileService;

    public MealServiceImpl(MealEntryRepo mealEntryRepository, UserProfileService userProfileService) {
        this.mealEntryRepository = mealEntryRepository;
        this.userProfileService = userProfileService;
    }

    @Override
    public MealEntry addMeal(UUID authUserId, MealEntry mealEntry) {
        // Найдем профиль пользователя по authUserId, чтобы привязать прием пищи
        UserProfile userProfile = userProfileService.getUserProfileByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));
        mealEntry.setUser(userProfile);
        // Если в mealEntry уже вложены FoodItem, не забывайте устанавливать ссылку на mealEntry в каждом FoodItem
        if (mealEntry.getItems() != null) {
            mealEntry.getItems().forEach(item -> item.setMealEntry(mealEntry));
        }
        return mealEntryRepository.save(mealEntry);
    }

    @Override
    public List<MealEntry> getMeals(UUID authUserId, LocalDate date) {
        UserProfile userProfile = userProfileService.getUserProfileByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));
        return mealEntryRepository.findAllByUserAndDate(userProfile, date);
    }

    @Override
    public MealEntry updateMeal(UUID authUserId, MealEntry mealEntry) {
        // Проверяем, что данный прием пищи принадлежит пользователю
        UserProfile userProfile = userProfileService.getUserProfileByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));
        // Допустим, что mealEntry имеет идентификатор, и мы его ищем
        Optional<MealEntry> existing = mealEntryRepository.findById(mealEntry.getId());
        if (existing.isEmpty() || !existing.get().getUser().getId().equals(userProfile.getId())) {
            throw new IllegalArgumentException("Прием пищи не найден или доступ запрещен");
        }
        // Обновляем данные. Если изменяются вложенные продукты, не забываем установить mealEntry для каждого FoodItem
        if (mealEntry.getItems() != null) {
            mealEntry.getItems().forEach(item -> item.setMealEntry(mealEntry));
        }
        return mealEntryRepository.save(mealEntry);
    }

    @Override
    public void deleteMeal(UUID authUserId, UUID mealEntryId) {
        UserProfile userProfile = userProfileService.getUserProfileByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));
        MealEntry mealEntry = mealEntryRepository.findById(mealEntryId)
                .orElseThrow(() -> new IllegalArgumentException("Прием пищи не найден"));
        if (!mealEntry.getUser().getId().equals(userProfile.getId())) {
            throw new IllegalArgumentException("Доступ запрещен");
        }
        mealEntryRepository.delete(mealEntry);
    }
}
