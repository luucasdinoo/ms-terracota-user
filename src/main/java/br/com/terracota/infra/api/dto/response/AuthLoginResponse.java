package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.AuthLoginOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthLoginResponse(
        String username,
        Boolean authenticated,
        String created,
        String expiration,
        @JsonProperty("access_token") String accessToken
) {
    public static AuthLoginResponse with(final AuthLoginOutput output) {
        return new AuthLoginResponse(
                output.username(),
                output.authenticated(),
                output.created().toString(),
                output.expiration().toString(),
                output.accessToken()
        );
    }
}
