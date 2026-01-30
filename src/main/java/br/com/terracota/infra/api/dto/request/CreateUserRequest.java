package br.com.terracota.infra.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotBlank @JsonProperty("user_type") String userType,
        @NotNull List<String> roles
) {
}
