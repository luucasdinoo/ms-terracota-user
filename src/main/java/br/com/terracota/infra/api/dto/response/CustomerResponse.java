package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.CustomerOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        String id,
        UserResponse user,
        DocumentResponse document,
        @JsonProperty("date_of_birthday") String dateOfBirthday,
        String createdAt,
        String updatedAt
) {
    public static CustomerResponse with(final CustomerOutput data){
        return new CustomerResponse(
                data.id(),
                UserResponse.with(data.user()),
                DocumentResponse.with(data.document()),
                data.dateOfBirthday(),
                data.createdAt(),
                data.updatedAt()
        );
    }
}
