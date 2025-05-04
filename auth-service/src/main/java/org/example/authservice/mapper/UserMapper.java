package org.example.authservice.mapper;

import org.example.authservice.DTO.request.RegisterUserRequest;
import org.example.authservice.DTO.response.LoginUserResponse;
import org.example.authservice.DTO.response.UserResponse;
import org.example.authservice.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity fromRegisterUserRequestToUserEntity(RegisterUserRequest registerUserRequest);

    LoginUserResponse fromUserEntityToLoginUserResponse(UserEntity userEntity, String token, String message);

    UserResponse fromUserEntityToUserResponse(UserEntity entity);

//    PublicUserResponse fromUserEntityToPublicUserResponse(UserEntity user);
//
//    List<PublicUserResponse> fromListUserEntityToListPublicUserResponse(List<UserEntity> list);
//
//    PutUserResponse fromUserEntityToPutUserResponse(UserEntity user);
//
//    UserEntity fromPutUserRequestToUpdateUserEntity(@MappingTarget UserEntity user, PutUserRequest userRequest);

}
