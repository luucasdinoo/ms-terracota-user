package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.Craftsman;

public record CraftsmanOutput(
        String id,
        UserOutput user,
        DocumentOutput document,
        String dateOfBirthday,
        String createdAt,
        String updatedAt
) {
    public static CraftsmanOutput with(final Craftsman craftsman){
        return new CraftsmanOutput(
                craftsman.getId(),
                UserOutput.with(craftsman.getUser().get()),
                DocumentOutput.with(craftsman.getDocument().get()),
                craftsman.getDateOfBirth().toString(),
                craftsman.getCreatedAt().toString(),
                craftsman.getUpdatedAt().toString()
        );
    }
}
