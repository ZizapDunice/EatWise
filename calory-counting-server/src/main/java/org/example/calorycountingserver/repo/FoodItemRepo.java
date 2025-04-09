package org.example.calorycountingserver.repo;

import org.example.calorycountingserver.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, UUID> {

}
