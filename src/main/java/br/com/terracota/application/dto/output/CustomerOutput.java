package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.Customer;

public record CustomerOutput(
        String id,
        UserOutput user,
        DocumentOutput document,
        String dateOfBirthday,
        String createdAt,
        String updatedAt
) {
    public static CustomerOutput with(final Customer customer){
        return new CustomerOutput(
                customer.getId(),
                UserOutput.with(customer.getUser().get()),
                DocumentOutput.with(customer.getDocument().get()),
                customer.getDateOfBirth().toString(),
                customer.getCreatedAt().toString(),
                customer.getUpdatedAt().toString()
        );
    }
}
