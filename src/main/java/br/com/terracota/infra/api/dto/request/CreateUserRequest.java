package br.com.terracota.infra.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotNull List<String> roles
) {
}
