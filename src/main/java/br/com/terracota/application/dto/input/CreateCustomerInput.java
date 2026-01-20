package br.com.terracota.application.dto.input;

import java.util.Date;

public record CreateCustomerInput(
    UserInput user,
    DocumentInput document,
    Date dateOfBirth
) {
    public static  CreateCustomerInput with(UserInput userInput, DocumentInput documentInput, Date dateOfBirth){
        return new CreateCustomerInput(userInput, documentInput, dateOfBirth);
    }
}
