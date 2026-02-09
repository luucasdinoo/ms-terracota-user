package br.com.terracota.application.dto.output;

import java.time.Instant;

public record AuthLoginOutput(
        String username,
        Boolean authenticated,
        Instant created,
        Instant expiration,
        String accessToken
) {
    public static AuthLoginOutput with(String username, Boolean authenticated, Instant created, Instant expiration, String accessToken){
        return new AuthLoginOutput(username, authenticated, created, expiration, accessToken);
    }
}
