package org.example.authservice.security;

import lombok.RequiredArgsConstructor;

import org.example.authservice.handling.CustomException;
import org.example.authservice.models.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.authservice.handling.ErrorCodes.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public CustomUserDetails loadUserByUsername(String uuid) throws CustomException {
        UserEntity user = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return new CustomUserDetails(user.getId(), user.getPassword(), user.getRoles());
    }
}
