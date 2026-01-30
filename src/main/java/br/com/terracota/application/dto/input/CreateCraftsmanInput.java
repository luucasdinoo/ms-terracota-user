package br.com.terracota.application.dto.input;

import java.util.Date;

public record CreateCraftsmanInput(
    UserInput user,
    DocumentInput document,
    Date dateOfBirth,
    String userType
) {
    public static CreateCraftsmanInput with(UserInput userInput, DocumentInput documentInput, Date dateOfBirth, String userType){
        return new CreateCraftsmanInput(userInput, documentInput, dateOfBirth, userType);
    }
}
