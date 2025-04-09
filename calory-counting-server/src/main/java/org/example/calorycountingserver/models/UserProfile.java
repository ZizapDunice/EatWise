package org.example.calorycountingserver.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Ссылка на идентификатор пользователя из auth-service
    // Этот идентификатор должен совпадать с тем, что хранится в таблице auth-service
    @Column(name = "auth_user_id", nullable = false, unique = true)
    private UUID authUserId;

    @Column(name = "gender", nullable = false)
    private String gender; // Например, "male", "female"

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "height", nullable = false)
    private int height; // в сантиметрах

    @Column(name = "weight", nullable = false)
    private double weight; // в килограммах

    @Column(name = "goal", nullable = false)
    private String goal; // например, "lose", "gain", "maintain"

    @Column(name = "activity_level", nullable = false)
    private String activityLevel; // "low", "moderate", "high"

    @ElementCollection
    @CollectionTable(name = "dietary_preferences", joinColumns = @JoinColumn(name = "user_profile_id"))
    @Column(name = "preference")
    private List<String> dietaryPreferences;
}
