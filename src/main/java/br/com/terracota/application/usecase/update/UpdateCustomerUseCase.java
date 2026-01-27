package br.com.terracota.application.usecase.update;

import br.com.terracota.application.UnitUseCase;
import br.com.terracota.application.dto.input.UpdateCustomerInput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CustomerNotFoundException;
import br.com.terracota.domain.exception.EmailAlreadyExistsException;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.exception.UsernameAlreadyExistsException;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCase extends UnitUseCase<UpdateCustomerInput> {

    private final CustomerGateway customerGateway;
    private final UserGateway userGateway;

    @Override
    public void execute(final UpdateCustomerInput input) {
        Customer customer = this.customerGateway.findById(input.id())
                .orElseThrow(() -> new CustomerNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF01));
        User user = customer.getUser()
                .orElseThrow(() -> new UserNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF03));

        boolean isValidUsername = input.username() != null && !input.username().isBlank();
        boolean isValidName = input.name() != null && !input.name().isBlank();
        boolean isValidEmail= input.email() != null && !input.email().isBlank();
        boolean isValidPhone= input.phone() != null && !input.phone().isBlank();

        if (isValidUsername && this.userGateway.existsByUsername(input.username())){
            throw new UsernameAlreadyExistsException(ExceptionType.ALREADY_EXISTS, ErrorCode.ECAE02);
        }

        if (isValidEmail && this.userGateway.existsByEmail(input.email())){
            throw new EmailAlreadyExistsException(ExceptionType.ALREADY_EXISTS, ErrorCode.ECAE03);
        }

        user = user.update(
                isValidUsername ? input.username() : user.getUsername(),
                isValidEmail ? input.email() : user.getEmail(),
                isValidName ? input.name() : user.getName(),
                isValidPhone ? input.phone() : user.getPhone()
        );
        this.userGateway.update(user);
        customer.setUpdatedAt(Instant.now());
        this.customerGateway.update(customer);
    }
}
