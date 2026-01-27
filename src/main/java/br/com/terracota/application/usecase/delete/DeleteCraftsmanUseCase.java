package br.com.terracota.application.usecase.delete;

import br.com.terracota.application.UnitUseCase;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CraftsmanNotFoundException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCraftsmanUseCase extends UnitUseCase<String> {

    private final CraftsmanGateway craftsmanGateway;

    @Override
    public void execute(final String input) {
        if (!this.craftsmanGateway.existsById(input)){
            throw new CraftsmanNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF02);
        }
        this.craftsmanGateway.deleteById(input);
    }
}
