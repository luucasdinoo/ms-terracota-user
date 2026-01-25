package br.com.terracota.application.usecase;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.input.CreateUserInput;
import br.com.terracota.application.dto.output.CreateUserOutput;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.exception.EntityNotFoundException;
import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase extends UseCase<CreateUserInput, CreateUserOutput> {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserOutput execute(final CreateUserInput input) {
        validateUserInput(input);

        Set<Role> roles = input.roles().stream()
                .map(role -> roleGateway.findByDescription(role)
                        .orElseThrow(() -> new EntityNotFoundException()))
                .collect(Collectors.toUnmodifiableSet());

        var user = User.create(
                input.username(),
                this.passwordEncoder.encode(input.password()),
                input.name(),
                input.email(),
                input.phone(),
                new ArrayList<>(),
                roles
        );
        user = this.userGateway.create(user);
        return new CreateUserOutput(user.getId());
    }

    private void validateUserInput(final CreateUserInput input){
        Optional<User> byEmail = this.userGateway.findByEmail(input.email());
        Optional<User> byUsername = this.userGateway.findByUsername(input.username());

        if (byEmail.isPresent() || byUsername.isPresent()) {
            throw new AlreadyExistsException();
        }
    }
}
