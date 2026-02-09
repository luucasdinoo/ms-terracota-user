package br.com.terracota.application.usecase.delete;

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

import static br.com.terracota.mock.UseCaseTestMocks.USER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private DeleteCustomerUseCase useCase;

    @Test
    @DisplayName("Given a valid id when execute then return void")
    void givenValidId_WhenCustomerExists_ThenReturnVoid(){
        when(this.customerGateway.existsById(anyString()))
                .thenReturn(Boolean.TRUE);

        this.useCase.execute(USER_ID);

        verify(this.customerGateway, times(1)).deleteById(eq(USER_ID));
    }

    @Test
    @DisplayName("Given a invalid id when execute then throw CustomerNotFoundException")
    void givenInvalidId_WhenCustomerNotFound_ThenThrowCustomerNotFoundException(){
        when(this.customerGateway.existsById(anyString()))
                .thenReturn(Boolean.FALSE);

        assertThatThrownBy(() -> this.useCase.execute(USER_ID))
                .isInstanceOf(CustomerNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF01, HttpStatus.NOT_FOUND);

        verify(this.customerGateway, never()).deleteById(eq(USER_ID));
    }
}
