package br.com.terracota.application.dto.input;

public record AuthLoginInput(
        String username,
        String password
) {
    public static AuthLoginInput with(final String username, final String password) {
        return new AuthLoginInput(username, password);
    }
}
