package br.com.terracota.application.usecase.create;

import br.com.terracota.application.dto.output.CreateUserOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.exception.RoleNotFoundException;
import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static br.com.terracota.mock.TestMocks.CREATE_USER_INPUT;
import static br.com.terracota.mock.TestMocks.USER_TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserGateway userGateway;
    @Mock
    private RoleGateway roleGateway;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Test
    @DisplayName("Given a valid input when execute then returns user id")
    void givenValidInput_WhenExecute_ThenReturnsUserId() {
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(this.roleGateway.findByDescription("USER"))
                .thenReturn(Optional.of(Role.create("USER")));
        when(this.passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(this.userGateway.create(any(User.class))).thenReturn(USER_TEST);

        CreateUserOutput output = this.useCase.execute(CREATE_USER_INPUT);

        assertThat(output).isNotNull();
        assertThat(output.id()).isNotNull();
    }

    @Test
    @DisplayName("Given a invalid input when role not found then throw RoleNotFoundException")
    void givenInvalidInput_WhenRoleNotFound_ThenThrowRoleNotFoundException() {
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(this.roleGateway.findByDescription(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CREATE_USER_INPUT))
                .isInstanceOf(RoleNotFoundException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.ECNF04);

    }
    @Test
    @DisplayName("Given a invalid input when user username exists then throw AlreadyExistsException")
    void givenInvalidInput_WhenUsernameExists_ThenThrowAlreadyExistsException() {
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.of(USER_TEST));
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CREATE_USER_INPUT))
                .isInstanceOf(AlreadyExistsException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.ECAE01);

        verify(this.userGateway, never()).create(any());
    }

    @Test
    @DisplayName("Given a invalid input when user email exists then throw AlreadyExistsException")
    void givenInvalidInput_WhenEmailExists_ThenThrowAlreadyExistsException() {
        when(this.userGateway.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(this.userGateway.findByEmail(anyString()))
                .thenReturn(Optional.of(USER_TEST));

        assertThatThrownBy(() -> this.useCase.execute(CREATE_USER_INPUT))
                .isInstanceOf(AlreadyExistsException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.ECAE01);

        verify(this.userGateway, never()).create(any());
    }
}
