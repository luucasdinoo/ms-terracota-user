package br.com.terracota.application.usecase.delete;

import br.com.terracota.application.UnitUseCase;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase extends UnitUseCase<String> {

    private final UserGateway userGateway;
    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;

    @Override
    public void execute(final String input) {
        User user = this.userGateway.findById(input)
                .orElseThrow(() -> new UserNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF03));

        this.customerGateway.findByUserId(user.getId())
                .ifPresent(this.customerGateway::delete);
        this.craftsmanGateway.findByUserId(user.getId())
                .ifPresent(this.craftsmanGateway::delete);

        this.userGateway.deleteById(user.getId());
    }
}
