package br.com.terracota.infra.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
