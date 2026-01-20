package br.com.terracota.application.dto.input;

import java.util.Date;

public record CreateCraftsmanInput(
    UserInput user,
    DocumentInput document,
    Date dateOfBirth
) {
    public static CreateCraftsmanInput with(UserInput userInput, DocumentInput documentInput, Date dateOfBirth){
        return new CreateCraftsmanInput(userInput, documentInput, dateOfBirth);
    }
}
