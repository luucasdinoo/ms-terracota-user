package br.com.terracota.application.usecase.delete;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.gateway.UserGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.terracota.mock.UseCaseTestMocks.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    private UserGateway userGateway;
    @Mock
    private CraftsmanGateway craftsmanGateway;
    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private DeleteUserUseCase useCase;

    @Test
    @DisplayName("Given a valid id when execute then return void")
    void givenValidId_WhenExecute_ThenReturnVoid(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.craftsmanGateway.findByUserId(anyString()))
                .thenReturn(Optional.empty());
        when(this.customerGateway.findByUserId(anyString()))
                .thenReturn(Optional.empty());

        this.useCase.execute(USER_ID);

        verify(this.userGateway, times(1)).deleteById(eq(USER_ID));
    }

    @Test
    @DisplayName("Given a valid id when customer exists then return void")
    void givenValidId_WhenCustomerExists_ThenReturnVoid(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.craftsmanGateway.findByUserId(anyString()))
                .thenReturn(Optional.empty());
        when(this.customerGateway.findByUserId(anyString()))
                .thenReturn(Optional.of(CUSTOMER_TEST));

        this.useCase.execute(USER_ID);

        verify(this.customerGateway, times(1)).delete(CUSTOMER_TEST);
        verify(this.userGateway, times(1)).deleteById(eq(USER_ID));
    }

    @Test
    @DisplayName("Given a valid id when craftsman exists then return void")
    void givenValidId_WhenCraftsmanExists_ThenReturnVoid(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.craftsmanGateway.findByUserId(anyString()))
                .thenReturn(Optional.of(CRAFTSMAN_TEST));
        when(this.customerGateway.findByUserId(anyString()))
                .thenReturn(Optional.empty());

        this.useCase.execute(USER_ID);

        verify(this.craftsmanGateway, times(1)).delete(CRAFTSMAN_TEST);
        verify(this.userGateway, times(1)).deleteById(eq(USER_ID));
    }

    @Test
    @DisplayName("Given a invalid id when user not found then throw UserNotFoundException")
    void givenInvalidId_WhenUserNotFound_ThenThrowUserNotFoundException(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(USER_ID))
                .isInstanceOf(UserNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF03, HttpStatus.NOT_FOUND);

        verify(this.craftsmanGateway, never()).findByUserId(eq(USER_ID));
        verify(this.customerGateway, never()).findByUserId(eq(USER_ID));
    }
}
