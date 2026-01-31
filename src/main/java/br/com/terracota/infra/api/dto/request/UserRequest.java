package br.com.terracota.infra.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username cannot be blank")
        @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 254, message = "Password must be between 8 and 254 characters")
        String password,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 254, message = "Name must be between 3 and 254 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Size(max = 254, message = "Emails must be up to 150 characters")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Phone cannot be blank")
        @Size(min = 11, max = 11, message = "Phone must be 11 characters")
        String phone,

        @NotBlank(message = "User type cannot be blank")
        @Size(min = 4, max = 15, message = "User type must be between 4 and 15 characters")
        @JsonProperty("user_type")
        String userType
) {
}
