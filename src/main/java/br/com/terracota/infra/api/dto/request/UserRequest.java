package br.com.terracota.infra.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotBlank @JsonProperty("user_type") String userType
) {
}
