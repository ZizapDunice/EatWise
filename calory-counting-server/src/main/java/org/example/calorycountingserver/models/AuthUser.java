package org.example.calorycountingserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    private UUID id;

    private String name;

    private String email;

    private boolean status = true;

    private String password;

    private Set<Role> roles;
}