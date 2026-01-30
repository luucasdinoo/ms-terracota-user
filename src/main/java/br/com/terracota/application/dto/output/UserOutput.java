package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.User;

import java.util.List;

public record UserOutput(
        String id,
        String username,
        String email,
        String phone,
        String userType,
        List<AddressOutput> addressOutput
) {
    public static UserOutput with(final User user) {
        return new UserOutput(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone(),
            user.getUserType().getDescription(),
            user.getAddresses().stream()
                .map(AddressOutput::with)
                .toList()
        );
    }
}
