package org.example.calorycountingserver.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserProfileResponse {

    private String name;

    private String email;

    private String gender;

    private int age;

    private int height;

    private double weight;

    private String goal;

    private String activityLevel;

    private List<String> dietaryPreferences;

}
