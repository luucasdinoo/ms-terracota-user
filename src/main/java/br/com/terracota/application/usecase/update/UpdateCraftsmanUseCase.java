package br.com.terracota.application.usecase.update;

import br.com.terracota.application.UnitUseCase;
import br.com.terracota.application.dto.input.UpdateCraftsmanInput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CraftsmanNotFoundException;
import br.com.terracota.domain.exception.EmailAlreadyExistsException;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.exception.UsernameAlreadyExistsException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UpdateCraftsmanUseCase extends UnitUseCase<UpdateCraftsmanInput> {

    private final CraftsmanGateway craftsmanGateway;
    private final UserGateway userGateway;

    @Override
    public void execute(final UpdateCraftsmanInput input) {
        Craftsman craftsman = this.craftsmanGateway.findById(input.id())
                .orElseThrow(() -> new CraftsmanNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF02));
        User user = craftsman.getUser()
                .orElseThrow(() -> new UserNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF03));

        boolean isValidUsername = input.username() != null && !input.username().isBlank();
        boolean isValidName = input.name() != null && !input.name().isBlank();
        boolean isValidEmail= input.email() != null && !input.email().isBlank();
        boolean isValidPhone= input.phone() != null && !input.phone().isBlank();

        if (isValidUsername && this.userGateway.existsByUsername(input.username())){
            throw new UsernameAlreadyExistsException(ExceptionType.ALREADY_EXISTS, ErrorCode.ECAE02);
        }

        if (isValidUsername && this.userGateway.existsByEmail(input.email())){
            throw new EmailAlreadyExistsException(ExceptionType.ALREADY_EXISTS, ErrorCode.ECAE03);
        }

        user = user.update(
                isValidUsername ? input.username() : user.getUsername(),
                isValidEmail ? input.email() : user.getEmail(),
                isValidName ? input.name() : user.getName(),
                isValidPhone ? input.phone() : user.getPhone()
        );
        this.userGateway.update(user);
        craftsman.setUpdatedAt(Instant.now());
        this.craftsmanGateway.update(craftsman);
    }
}
