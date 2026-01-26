package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.User;

public record UserOutputWithoutAddress(
    String id,
    String username,
    String email,
    String phone
) {
    public static UserOutputWithoutAddress with(final User user) {
        return new UserOutputWithoutAddress(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone()
        );
    }
}
