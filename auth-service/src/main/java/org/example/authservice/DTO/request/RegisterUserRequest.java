package org.example.authservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.authservice.constants.ValidationConstants;
import org.example.authservice.models.RoleEnum;

import java.util.Set;

@Data
public class RegisterUserRequest {

    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @Size(min = 3, max = 25, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

    @NotBlank(message = ValidationConstants.USER_PASSWORD_NULL)
    private String password;

    @Size(min = 1, message = ValidationConstants.ROLE_SIZE_NOT_VALID)
    @NotEmpty(message = ValidationConstants.ROLE_SIZE_NOT_VALID)
    private Set<RoleEnum> roles;

}
