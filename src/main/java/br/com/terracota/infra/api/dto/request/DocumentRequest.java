package br.com.terracota.infra.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentRequest(
        @JsonProperty("document")
        String value,

        @JsonProperty("document_type")
        String documentType
) {
}
