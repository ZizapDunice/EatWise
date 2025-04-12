package org.example.calorycountingserver.services;

import org.example.calorycountingserver.models.MealEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MealService {
    MealEntry addMeal(UUID authUserId, MealEntry mealEntry);
    List<MealEntry> getMeals(UUID authUserId, LocalDate date);
    MealEntry updateMeal(UUID authUserId, MealEntry mealEntry);
    void deleteMeal(UUID authUserId, UUID mealEntryId);
}
