package br.com.terracota.infra.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DocumentRequest(
        @JsonProperty("document") @NotBlank String value,
        @JsonProperty("document_type") @NotBlank String documentType
) {
}
