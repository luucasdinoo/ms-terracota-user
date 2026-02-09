package br.com.terracota.application.usecase.update;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.CustomerNotFoundException;
import br.com.terracota.domain.exception.EmailAlreadyExistsException;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.exception.UsernameAlreadyExistsException;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.terracota.mock.UseCaseTestMocks.CUSTOMER_TEST;
import static br.com.terracota.mock.UseCaseTestMocks.UPDATE_CUSTOMER_INPUT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseTest {

    @Mock
    private UserGateway userGateway;
    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private UpdateCustomerUseCase useCase;

    @Test
    @DisplayName("Given a valid input when execute then returns void")
    void givenValidInput_WhenExecute_ThenReturnsVoid(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));
        when(this.userGateway.existsByUsername(anyString()))
                .thenReturn(Boolean.FALSE);
        when(this.userGateway.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        this.useCase.execute(UPDATE_CUSTOMER_INPUT);

        verify(this.userGateway).update(any(User.class));
        verify(this.customerGateway).update(CUSTOMER_TEST);
    }

    @Test
    @DisplayName("Given a valid input when username already exists then throw UsernameAlreadyExistsException")
    void givenValidInput_WhenUsernameAlreadyExists_ThenThrowUsernameAlreadyExistsException(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));
        when(this.userGateway.existsByUsername(anyString()))
                .thenReturn(Boolean.TRUE);

        assertThatThrownBy(() -> this.useCase.execute(UPDATE_CUSTOMER_INPUT))
                .isInstanceOf(UsernameAlreadyExistsException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECAE02, HttpStatus.CONFLICT);


        verify(this.userGateway, never()).update(any(User.class));
        verify(this.customerGateway, never()).update(CUSTOMER_TEST);
    }

    @Test
    @DisplayName("Given a valid input when email already exists then throw EmailAlreadyExistsException")
    void givenValidInput_WhenEmailAlreadyExists_ThenThrowEmailAlreadyExistsException(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));
        when(this.userGateway.existsByUsername(anyString()))
                .thenReturn(Boolean.FALSE);
        when(this.userGateway.existsByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        assertThatThrownBy(() -> this.useCase.execute(UPDATE_CUSTOMER_INPUT))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECAE03, HttpStatus.CONFLICT);


        verify(this.userGateway, never()).update(any(User.class));
        verify(this.customerGateway, never()).update(CUSTOMER_TEST);
    }

    @Test
    @DisplayName("Given a valid input when customer not found then throw CustomerNotFoundException")
    void givenValidInput_WhenCustomerNotFound_ThenThrowCustomerNotFoundException(){
        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(UPDATE_CUSTOMER_INPUT))
                .isInstanceOf(CustomerNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF01, HttpStatus.NOT_FOUND);

        verify(this.userGateway, never()).update(any(User.class));
        verify(this.customerGateway, never()).update(CUSTOMER_TEST);
    }

    @Test
    @DisplayName("Given a valid input when user not found then throw UserNotFoundException")
    void givenValidInput_WhenUserNotFound_ThenThrowUserNotFoundException(){
        Customer customerMock = mock(Customer.class);

        when(this.customerGateway.findById(anyString()))
                .thenReturn(Optional.of(customerMock));
        when(customerMock.getUser())
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(UPDATE_CUSTOMER_INPUT))
                .isInstanceOf(UserNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF03, HttpStatus.NOT_FOUND);

        verify(this.userGateway, never()).update(any(User.class));
        verify(this.customerGateway, never()).update(CUSTOMER_TEST);
    }
}
