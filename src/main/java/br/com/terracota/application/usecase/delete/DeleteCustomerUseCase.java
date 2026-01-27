package br.com.terracota.application.usecase.delete;

import br.com.terracota.application.UnitUseCase;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CustomerNotFoundException;
import br.com.terracota.domain.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase extends UnitUseCase<String> {

    private final CustomerGateway customerGateway;

    @Override
    public void execute(final String input) {
        if (!this.customerGateway.existsById(input)){
            throw new CustomerNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF01);
        }
        this.customerGateway.deleteById(input);
    }
}
