package br.com.terracota.application.dto.input;

import java.util.List;

public record CreateUserInput(
        String username,
        String password,
        String name,
        String email,
        String phone,
        String userType,
        List<String> roles
) {
    public static CreateUserInput with(
            final String username,
            final String password,
            final String name,
            final String email,
            final String phone,
            String userType,
            final List<String> roles
    ) {
        return new CreateUserInput(username, password, name, email, phone, userType, roles);
    }
}
