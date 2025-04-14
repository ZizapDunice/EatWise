package org.example.authservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.authservice.constants.Constants;
import org.example.authservice.constants.ValidationConstants;

@Data
public class PutUserRequest {

    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @Pattern(regexp = Constants.EMAIL_VALIDATION, message = ValidationConstants.USER_EMAIL_NOT_VALID)@NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    private String email;

    @Size(min = 3, max = 25, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

}
