package br.com.terracota.infra.api.dto.request;

public record UpdateCraftsmanRequest(
        String username,
        String email,
        String name,
        String phone
) {
}
