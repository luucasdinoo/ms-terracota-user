package br.com.terracota.application.dto.input;

public record UpdateCustomerInput(
        String id,
        String username,
        String email,
        String name,
        String phone
) {
    public static UpdateCustomerInput with(
            final String id,
            final String username,
            final String email,
            final String name,
            final String phone
    ) {
        return new UpdateCustomerInput(id, username, email, name, phone);
    }
}
