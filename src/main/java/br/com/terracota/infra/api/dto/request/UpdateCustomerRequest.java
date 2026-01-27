package br.com.terracota.infra.api.dto.request;

public record UpdateCustomerRequest(
        String username,
        String email,
        String name,
        String phone
) {
}
