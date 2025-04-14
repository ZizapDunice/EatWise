package org.example.authservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.authservice.constants.Constants;
import org.example.authservice.constants.ValidationConstants;

@Data
public class AuthUserRequest {

    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Pattern(regexp = Constants.EMAIL_VALIDATION, message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = ValidationConstants.PASSWORD_NOT_VALID)
    private String password;
}
