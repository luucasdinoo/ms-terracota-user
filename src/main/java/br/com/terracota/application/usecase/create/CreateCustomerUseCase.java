package br.com.terracota.application.usecase.create;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.input.CreateCustomerInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CreateCustomerOutput;
import br.com.terracota.domain.enums.DocumentType;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.enums.TypeUser;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.Document;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateCustomerUseCase extends UseCase<CreateCustomerInput, CreateCustomerOutput> {

    private final CustomerGateway customerGateway;
    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateCustomerOutput execute(final CreateCustomerInput input) {
        UserInput userInput = input.user();
        DocumentInput documentInput = input.document();
        Optional<Role> role = this.roleGateway.findByDescription("CUSTOMER");

        validateUserInput(userInput, documentInput);

        var user = User.create(
                userInput.username(),
                this.passwordEncoder.encode(userInput.password()),
                userInput.name(),
                userInput.email(),
                userInput.phone(),
                TypeUser.valueOf(input.userType()),
                new ArrayList<>(),
                Set.of(role.get())
        );
        this.userGateway.create(user);
        var document = Document.create(documentInput.value(), DocumentType.valueOf(documentInput.documentType()));
        var customer = Customer.create(user, document, input.dateOfBirth());

        customer = this.customerGateway.create(customer);
        return new CreateCustomerOutput(customer.getId());
    }

    private void validateUserInput(final UserInput userInput, final DocumentInput documentInput) {
        Optional<User> byEmail = this.userGateway.findByEmail(userInput.email());
        Optional<User> byUsername = this.userGateway.findByUsername(userInput.username());
        Optional<Customer> byDocument = this.customerGateway.findByDocumentValue(documentInput.value());

        if (byEmail.isPresent() || byUsername.isPresent() || byDocument.isPresent()) {
            throw new AlreadyExistsException(ExceptionType.ALREADY_EXISTS, ErrorCode.ECAE01);
        }
    }
}
