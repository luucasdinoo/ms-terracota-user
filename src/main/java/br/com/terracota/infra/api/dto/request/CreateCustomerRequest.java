package br.com.terracota.infra.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateCustomerRequest(
        @NotNull UserRequest user,
        @NotNull DocumentRequest document,
        @JsonProperty("date_of_birthday") @NotNull Date dateOfBirthday
) {
}
