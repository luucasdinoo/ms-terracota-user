package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.UserOutput;

public record UserResponse(
        String id,
        String username,
        String email,
        String phone
) {
    public static UserResponse with(final UserOutput data){
        return new UserResponse(
                data.id(),
                data.username(),
                data.email(),
                data.phone()
        );
    }
}
