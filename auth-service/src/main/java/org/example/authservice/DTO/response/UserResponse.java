package org.example.authservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authservice.models.RoleEnum;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;

    private UUID id;

    private String name;

    private Set<RoleEnum> roles;
}
