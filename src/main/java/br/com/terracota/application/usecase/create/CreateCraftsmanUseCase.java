package br.com.terracota.application.usecase.create;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.input.CreateCraftsmanInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CreateCraftsmanOutput;
import br.com.terracota.domain.enums.DocumentType;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Craftsman;
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
public class CreateCraftsmanUseCase extends UseCase<CreateCraftsmanInput, CreateCraftsmanOutput> {

    private final CraftsmanGateway craftsmanGateway;
    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateCraftsmanOutput execute(final CreateCraftsmanInput input) {
        UserInput userInput = input.user();
        DocumentInput documentInput = input.document();
        Optional<Role> role = this.roleGateway.findByDescription("CRAFTSMAN");

        validateUserInput(userInput, documentInput);

        var user = User.create(
                userInput.username(),
                this.passwordEncoder.encode(userInput.password()),
                userInput.name(),
                userInput.email(),
                userInput.phone(),
                new ArrayList<>(),
                Set.of(role.get())
        );
        this.userGateway.create(user);
        var document = Document.create(documentInput.value(), DocumentType.valueOf(documentInput.documentType()));
        var craftsman = Craftsman.create(user, document, input.dateOfBirth());

        craftsman = this.craftsmanGateway.create(craftsman);
        return new CreateCraftsmanOutput(craftsman.getId());
    }

    private void validateUserInput(final UserInput input, final DocumentInput documentInput){
        Optional<User> byEmail = this.userGateway.findByEmail(input.email());
        Optional<User> byUsername = this.userGateway.findByUsername(input.username());
        Optional<Craftsman> byDocument = this.craftsmanGateway.findByDocumentValue(documentInput.value());

        if (byEmail.isPresent() || byUsername.isPresent() || byDocument.isPresent()) {
            throw new AlreadyExistsException();
        }
    }
}
