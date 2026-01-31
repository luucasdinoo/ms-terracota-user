package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.UserOutputWithoutAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseWithoutAddress(
        String id,
        String username,
        String email,
        String phone,
        String name,
        @JsonProperty("user_type") String userType

        ) {
    public static UserResponseWithoutAddress with(final UserOutputWithoutAddress data){
        return new UserResponseWithoutAddress(
                data.id(),
                data.username(),
                data.email(),
                data.phone(),
                data.name(),
                data.userType().toUpperCase()
        );
    }
}
