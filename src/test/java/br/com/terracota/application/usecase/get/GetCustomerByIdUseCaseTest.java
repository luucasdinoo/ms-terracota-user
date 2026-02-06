package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.CustomerOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.CustomerNotFoundException;
import br.com.terracota.domain.gateway.CustomerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.terracota.mock.TestMocks.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCustomerByIdUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private GetCustomerByIdUseCase useCase;

    @Test
    @DisplayName("Given a valid id when execute then return customer output")
    void givenValidId_WhenExecute_ThenReturnUserOutput(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));

        CustomerOutput output = this.useCase.execute(CUSTOMER_ID);

        assertThat(output).isNotNull();
        assertThat(output.user()).isNotNull();
        assertThat(output.document()).isNotNull();
        assertThat(output.createdAt()).isNotNull();
        assertThat(output.updatedAt()).isNotNull();
        assertThat(output.id()).isEqualTo(CUSTOMER_TEST.getId());
    }

    @Test
    @DisplayName("Given a invalid id when execute then throw CustomerNotFoundException")
    void givenInvalidId_WhenExecute_ThenThrowCustomerNotFoundException(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CUSTOMER_ID))
                .isInstanceOf(CustomerNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF01, HttpStatus.NOT_FOUND);
    }
}
