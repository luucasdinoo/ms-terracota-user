package br.com.terracota.application.usecase.create;

import br.com.terracota.application.dto.output.CreateCustomerOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static br.com.terracota.mock.UseCaseTestMocks.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;
    @Mock
    private UserGateway userGateway;
    @Mock
    private RoleGateway roleGateway;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateCustomerUseCase useCase;

    @Test
    @DisplayName("Given a valid input when execute then returns customer id")
    void givenValidInput_WhenExecute_ThenReturnsCustomerId() {
        when(this.roleGateway.findByDescription("CUSTOMER"))
                .thenReturn(Optional.of(Role.create("CUSTOMER")));
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(this.customerGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.empty());
        when(this.passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");
        when(this.userGateway.create(any(User.class)))
                .thenReturn(USER_CUSTOMER_TEST);
        when(this.customerGateway.create(any(Customer.class)))
                .thenReturn(CUSTOMER_TEST);

        CreateCustomerOutput output = this.useCase.execute(CREATE_CUSTOMER_INPUT);

        assertThat(output).isNotNull();
        assertThat(output.id()).isNotNull();
    }

    @Test
    @DisplayName("Given a valid input when user username exists then throw AlreadyExistsException")
    void givenInvalidInput_WhenUsernameExists_ThenThrowAlreadyExistsException() {
        when(this.roleGateway.findByDescription("CUSTOMER"))
                .thenReturn(Optional.of(Role.create("CUSTOMER")));
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(this.customerGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CREATE_CUSTOMER_INPUT))
                .isInstanceOf(AlreadyExistsException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECAE01, HttpStatus.CONFLICT);

        verify(this.userGateway, never()).create(any(User.class));
        verify(this.customerGateway, never()).create(any(Customer.class));
    }

    @Test
    @DisplayName("Given a valid input when user email exists then throw AlreadyExistsException")
    void givenInvalidInput_WhenEmailExists_ThenThrowAlreadyExistsException() {
        when(this.roleGateway.findByDescription("CUSTOMER"))
                .thenReturn(Optional.of(Role.create("CUSTOMER")));
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.customerGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CREATE_CUSTOMER_INPUT))
                .isInstanceOf(AlreadyExistsException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECAE01, HttpStatus.CONFLICT);

        verify(this.userGateway, never()).create(any(User.class));
        verify(this.customerGateway, never()).create(any(Customer.class));
    }

    @Test
    @DisplayName("Given a valid input when user document exists then throw AlreadyExistsException")
    void givenInvalidInput_WhenDocumentExists_ThenThrowAlreadyExistsException() {
        when(this.roleGateway.findByDescription("CUSTOMER"))
                .thenReturn(Optional.of(Role.create("CUSTOMER")));
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(this.customerGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));

        assertThatThrownBy(() -> this.useCase.execute(CREATE_CUSTOMER_INPUT))
                .isInstanceOf(AlreadyExistsException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECAE01, HttpStatus.CONFLICT);

        verify(this.userGateway, never()).create(any(User.class));
        verify(this.customerGateway, never()).create(any(Customer.class));
    }
}
