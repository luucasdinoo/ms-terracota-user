package br.com.terracota.infra.api.dto.request;

import br.com.terracota.infra.ValidDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateCustomerRequest(
        @NotNull(message = "The user field cannot be null")
        @Valid UserRequest user,

        @NotNull(message = "The document field cannot be null")
        @ValidDocument()
        @Valid DocumentRequest document,

        @JsonProperty("date_of_birthday")
        @NotNull(message = "The date of birthday cannot be null")
        Date dateOfBirthday
) {
}
