package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.DocumentOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentResponse(
        String id,
        String value,
        @JsonProperty("document_type") String documentType
) {
    public static DocumentResponse with(final DocumentOutput data){
        return new DocumentResponse(
                data.id(),
                data.value(),
                data.documentType()
        );
    }
}
