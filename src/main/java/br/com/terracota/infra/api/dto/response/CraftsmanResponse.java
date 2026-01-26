package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.CraftsmanOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CraftsmanResponse(
        String id,
        UserResponseWithoutAddress user,
        DocumentResponse document,
        @JsonProperty("date_of_birthday") String dateOfBirthday,
        String createdAt,
        String updatedAt
) {
    public static CraftsmanResponse with(final CraftsmanOutput data){
        return new CraftsmanResponse(
                data.id(),
                UserResponseWithoutAddress.with(data.user()),
                DocumentResponse.with(data.document()),
                data.dateOfBirthday(),
                data.createdAt(),
                data.updatedAt()
        );
    }
}
