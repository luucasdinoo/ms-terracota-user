package br.com.terracota.application.dto.input;

public record UpdateCraftsmanInput(
        String id,
        String username,
        String email,
        String name,
        String phone
) {
    public static UpdateCraftsmanInput with(
            final String id,
            final String username,
            final String email,
            final String name,
            final String phone
    ) {
        return new UpdateCraftsmanInput(id, username, email, name, phone);
    }
}
