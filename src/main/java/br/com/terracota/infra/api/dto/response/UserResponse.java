package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.UserOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserResponse(
        String id,
        String username,
        String email,
        String phone,
        @JsonProperty("user_type") String userType,
        List<AddressResponse> address
) {
    public static UserResponse with(final UserOutput data){
        return new UserResponse(
                data.id(),
                data.username(),
                data.email(),
                data.phone(),
                data.userType().toUpperCase(),
                data.addressOutput().stream()
                        .map(AddressResponse::with)
                        .toList()
        );
    }
}
