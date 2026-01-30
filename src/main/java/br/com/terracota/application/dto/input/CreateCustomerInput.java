package br.com.terracota.application.dto.input;

import java.util.Date;

public record CreateCustomerInput(
    UserInput user,
    DocumentInput document,
    Date dateOfBirth,
    String userType
) {
    public static  CreateCustomerInput with(UserInput userInput, DocumentInput documentInput, Date dateOfBirth, String userType){
        return new CreateCustomerInput(userInput, documentInput, dateOfBirth, userType);
    }
}
