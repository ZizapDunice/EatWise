package org.example.calorycountingserver.repo;

import org.example.calorycountingserver.models.MealEntry;
import org.example.calorycountingserver.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MealEntryRepo extends JpaRepository<MealEntry, UUID> {
    List<MealEntry> findAllByUserAndDate(UserProfile user, LocalDate date);
}
