package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.AuthLoginOutput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthLoginResponse(
        String username,
        Boolean authenticated,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") String created,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") String expiration,
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
