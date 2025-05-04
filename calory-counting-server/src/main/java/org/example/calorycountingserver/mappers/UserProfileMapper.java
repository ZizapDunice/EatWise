package org.example.calorycountingserver.mappers;

import org.example.calorycountingserver.DTO.request.CreateUserProfileRequest;
import org.example.calorycountingserver.DTO.response.CreateUserProfileResponse;
import org.example.calorycountingserver.models.AuthUser;
import org.example.calorycountingserver.models.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

    UserProfile toEntity(CreateUserProfileRequest request, UUID authUserId);

    CreateUserProfileResponse toDto(UserProfile userProfile, AuthUser authUser);
}
