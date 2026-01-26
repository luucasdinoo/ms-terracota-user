package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.UserOutputWithoutAddress;

public record UserResponseWithoutAddress(
        String id,
        String username,
        String email,
        String phone
) {
    public static UserResponseWithoutAddress with(final UserOutputWithoutAddress data){
        return new UserResponseWithoutAddress(
                data.id(),
                data.username(),
                data.email(),
                data.phone()
        );
    }
}
