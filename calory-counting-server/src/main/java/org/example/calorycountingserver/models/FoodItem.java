package org.example.calorycountingserver.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "food_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Связь с приемом пищи, в котором используется данный продукт
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_entry_id", nullable = false)
    private MealEntry mealEntry;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "protein", nullable = false)
    private double protein;

    @Column(name = "fats", nullable = false)
    private double fats;

    @Column(name = "carbs", nullable = false)
    private double carbs;

    @Column(name = "portion_size", nullable = false)
    private double portionSize; // Например, в граммах
}
