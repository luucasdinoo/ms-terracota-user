package br.com.terracota.infra.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthLoginResponse(
        @JsonProperty("access_token") String accessToken
) {
}
