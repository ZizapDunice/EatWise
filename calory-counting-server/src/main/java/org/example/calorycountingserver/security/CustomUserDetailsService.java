package org.example.calorycountingserver.security;

import lombok.RequiredArgsConstructor;
import org.example.calorycountingserver.handling.CustomException;
import org.example.calorycountingserver.models.AuthUser;
import org.example.calorycountingserver.services.UserProfileService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserProfileService service;

    @Override
    public CustomUserDetails loadUserByUsername(String uuid) throws CustomException {
        AuthUser user = service.getAuthUser(UUID.fromString(uuid));
        return new CustomUserDetails(user.getId(), user.getPassword(), user.getRoles());
    }
}
