package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.Document;

public record DocumentOutput(
        String id,
        String value,
        String documentType
) {
    public static DocumentOutput with(final Document document){
        return new DocumentOutput(
                document.getId(),
                document.getValue(),
                document.getDocumentType().name()
        );
    }
}
