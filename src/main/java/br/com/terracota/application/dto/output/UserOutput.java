package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.User;

public record UserOutput(
    String id,
    String username,
    String email,
    String phone
) {
    public static UserOutput with(final User user) {
        return new UserOutput(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone()
        );
    }
}
