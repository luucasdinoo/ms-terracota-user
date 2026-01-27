package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.CustomerOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CustomerNotFoundException;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCustomerByIdUseCase extends UseCase<String, CustomerOutput> {
    
    private final CustomerGateway customerGateway;

    @Override
    public CustomerOutput execute(final String id) {
        Customer customer = this.customerGateway.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(ExceptionType.ENTITY_NOT_FOUND, ErrorCode.ECNT01));
        return CustomerOutput.with(customer);
    }
}
